package com.esri;

import com.lmax.disruptor.WorkHandler;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

/**
 */
public final class ValueHandler implements WorkHandler<ValueEvent>
{
    private final Pattern pattern = Pattern.compile("\t");

    private Cache m_cache;
    private AtomicLong m_objectId;

    public ValueHandler(
            final Cache cache,
            final AtomicLong objectId)
    {
        m_cache = cache;
        m_objectId = objectId;
    }

    @Override
    public void onEvent(final ValueEvent valueEvent) throws Exception
    {
        final String[] tokens = pattern.split(valueEvent.getValue());

        final InfoUSA infoUSA = new InfoUSA();

        infoUSA.objectId = m_objectId.incrementAndGet(); // Long.parseLong(tokens[0]);
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

        m_cache.put(new Element(infoUSA.objectId, infoUSA), false);
    }

}
