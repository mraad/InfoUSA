package com.esri;

import com.lmax.disruptor.FatalExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkerPool;
import com.lmax.disruptor.YieldingWaitStrategy;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 */
public class AppLoad
{
    final static Log LOG = LogFactory.getLog(AppLoad.class);

    public static void main(final String[] args) throws IOException
    {
        if (args.length == 0)
        {
            System.err.format("Usage: %s <filename>\n", AppLoad.class.getName());
        }
        else
        {
            final CacheManager cacheManager = CacheManager.newInstance(AppLoad.class.getResource("/ehcache.xml"));

            final Cache cache = cacheManager.getCache("InfoUSA");
            final AtomicLong objectId = new AtomicLong(0);

            final ValueHandler[] valueHandlers = new ValueHandler[]{
                    new ValueHandler(cache, objectId),
                    new ValueHandler(cache, objectId),
                    new ValueHandler(cache, objectId),
                    new ValueHandler(cache, objectId)
            };

            final ExecutorService executorService = Executors.newCachedThreadPool();
            try
            {
                final RingBuffer<ValueEvent> ringBuffer =
                        RingBuffer.createSingleProducer(ValueEvent.EVENT_FACTORY,
                                4096,
                                new YieldingWaitStrategy());

                final WorkerPool<ValueEvent> workerPool =
                        new WorkerPool<ValueEvent>(ringBuffer,
                                ringBuffer.newBarrier(),
                                new FatalExceptionHandler(),
                                valueHandlers);

                workerPool.start(executorService);
                try
                {
                    readFile(ringBuffer, args[0]);
                }
                finally
                {
                    workerPool.drainAndHalt();
                }
            }
            finally
            {
                executorService.shutdown();
            }
            LOG.info(String.format("Put in cache %d elements.\n", objectId.get()));
        }
    }

    private static void readFile(
            final RingBuffer<ValueEvent> ringBuffer,
            final String filename) throws IOException
    {
        int count = 0;
        final BufferedReader bufferedReader = new BufferedReader(new FileReader(filename), 1024 * 1024);
        try
        {
            String line = bufferedReader.readLine(); // Skip Header
            while ((line = bufferedReader.readLine()) != null)
            {
                final long sequence = ringBuffer.next();
                ringBuffer.get(sequence).setValue(line);
                ringBuffer.publish(sequence);
                count++;
            }
        }
        finally
        {
            bufferedReader.close();
        }
        LOG.info(String.format("Preloaded %d lines.\n", count));
    }
}
