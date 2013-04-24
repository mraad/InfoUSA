package com.esri;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 */
public class AppLoad2
{
    public static void main(final String[] args) throws IOException
    {
        if (args.length == 0)
        {
            System.err.format("Usage: %s <filename>\n", AppLoad2.class.getName());
        }
        else
        {
            final CacheManager cacheManager = CacheManager.newInstance(AppLoad2.class.getResource("/ehcache.xml"));

            final Cache cache = cacheManager.getCache("InfoUSA");
            cache.setNodeBulkLoadEnabled(true);
            try
            {
                bulkLoad(cache, args[0]);
            }
            finally
            {
                cache.setNodeBulkLoadEnabled(false);
            }
        }
    }

    private static void bulkLoad(
            final Cache cache,
            final String filename
    ) throws IOException
    {
        int count = 0;
        final BufferedReader bufferedReader = new BufferedReader(new FileReader(filename), 1024 * 1024);
        try
        {
            final Pattern pattern = Pattern.compile("\t");
            String line = bufferedReader.readLine(); // Skip Header
            while ((line = bufferedReader.readLine()) != null)
            {
                final String[] tokens = pattern.split(line);

                final InfoUSA infoUSA = new InfoUSA();

                infoUSA.objectId = Long.parseLong(tokens[0]);
                infoUSA.name = tokens[1];
                infoUSA.addr = tokens[2];
                infoUSA.city = tokens[3];
                infoUSA.state = tokens[4];
                // infoUSA.stateName = tokens[5];
                infoUSA.zip = tokens[6];
                infoUSA.sic = tokens[7];
                infoUSA.naicsExt = tokens[8];
                infoUSA.salesVol = Long.parseLong(tokens[9]);
                infoUSA.hdbrch = tokens[10];
                infoUSA.numEmp = Long.parseLong(tokens[11]);
                infoUSA.empSize = tokens[12];
                infoUSA.frnCode = tokens[13];
                infoUSA.isCode = tokens[14];
                infoUSA.sqft = tokens[15];
                infoUSA.subNum = Long.parseLong(tokens[16]);
                infoUSA.ultNum = Long.parseLong(tokens[17]);
                infoUSA.matchCode = tokens[18];
                infoUSA.locNum = Long.parseLong(tokens[19]);
                infoUSA.y = WebMercator.latitudeToY(Double.parseDouble(tokens[20]));
                infoUSA.x = WebMercator.longitudeToX(Double.parseDouble(tokens[21]));

                cache.put(new Element(infoUSA.objectId, infoUSA));

                count++;
            }
        }
        finally
        {
            bufferedReader.close();
        }
        System.out.format("Loaded %d elements.\n", count);
    }
}
