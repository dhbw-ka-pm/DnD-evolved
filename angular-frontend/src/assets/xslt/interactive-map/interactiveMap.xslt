<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <!-- <html> -->
    <!--   <body> -->

    <svg
      version="1.1"
      width="1000" height="1000"
      xmlns="http://www.w3.org/2000/svg">
      <g>
        <image x="0" y="0">
          <xsl:attribute name="href">
            <xsl:value-of select="Map/ImagePath"></xsl:value-of>
          </xsl:attribute>
        </image>

      </g>
      <g>
        <xsl:for-each select="/Map/Event/entry/value">

          <image href="/assets/icons/mappointer-v1.svg" width="60" height="60">
            <xsl:attribute name="x">
              <xsl:value-of select="x"></xsl:value-of>
            </xsl:attribute>
            <xsl:attribute name="y">
              <xsl:value-of select="y"></xsl:value-of>
            </xsl:attribute>

          </image>

        </xsl:for-each>

      </g>

    </svg>
    <!--   </body> -->

    <!-- </html> -->

  </xsl:template>
</xsl:stylesheet>
