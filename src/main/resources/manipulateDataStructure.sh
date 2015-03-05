#!/bin/bash
XSD_DIRECTORY=target/generated-sources/aqSchema

sed -i -r 's/type="q[^:]+:DateTimeOffset"/type="xs:dateTime"/g' $XSD_DIRECTORY/*.xsd
sed -i -r 's/type="q[^:]+:ArrayOfstring"/type="xs:string" maxOccurs="unbounded"/g' $XSD_DIRECTORY/*.xsd
sed -i -r 's/type="q[^:]+:ArrayOfdouble"/type="xs:double" maxOccurs="unbounded"/g' $XSD_DIRECTORY/*.xsd
sed -i -r 's/type="q[^:]+:ArrayOfNullableOfdouble"/type="xs:double" maxOccurs="unbounded"/g' $XSD_DIRECTORY/*.xsd
sed -i -r 's/type="q([^:]+):ArrayOf([^"]+)"/type="q\1:\2" maxOccurs="unbounded"/g' $XSD_DIRECTORY/*.xsd
sed -i -r 's/type="q([^:]+):KeyValueOfstringstring" maxOccurs="unbounded"/type="q\1:ArrayOfKeyValueOfstringstring"/g' $XSD_DIRECTORY/*.xsd
sed -i -r 's/minOccurs="0" name="([^"]+)" nillable="true" type="tns:ArrayOf([^"]+)"/minOccurs="0" name="\1" nillable="true" type="tns:\2" maxOccurs="unbounded"/g' $XSD_DIRECTORY/*.xsd
sed -i -r 's/minOccurs="0" name="[^"]+" nillable="true" type="tns:DoubleWithDisplay"/minOccurs="0" name="Value" nillable="true" type="xs:double"/g' $XSD_DIRECTORY/*.xsd
sed -i 's/AuthenticateResponse/AuthenticateResponseObj/g' $XSD_DIRECTORY/*.xsd