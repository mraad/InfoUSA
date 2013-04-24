package com.esri;

import net.sf.ehcache.Element;
import net.sf.ehcache.search.attribute.AttributeExtractor;
import net.sf.ehcache.search.attribute.AttributeExtractorException;

import java.util.Properties;

/**
 */
public class GeoHashExtractor implements AttributeExtractor
{
    private double m_xofs;
    private double m_yofs;
    private double m_cell;

    public GeoHashExtractor()
    {
    }


    public GeoHashExtractor(final Properties properties)
    {
        m_xofs = WebMercator.longitudeToX(Double.parseDouble(properties.getProperty("xofs", "-180.0")));
        m_yofs = WebMercator.latitudeToY(Double.parseDouble(properties.getProperty("yofs", "-90.0")));
        m_cell = Double.parseDouble(properties.getProperty("cell", "1.0"));
    }

    public Object attributeFor(
            final Element element,
            final String s) throws AttributeExtractorException
    {
        final InfoUSA infoUSA = (InfoUSA) element.getObjectValue();

        final long x = (long) Math.floor((infoUSA.x - m_xofs) / m_cell) & 0x7FFFL;
        final long y = (long) Math.floor((infoUSA.y - m_yofs) / m_cell) & 0x7FFFL;
        return (y << 16) | x;
    }

}
