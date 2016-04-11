#!/bin/bash
XSD_DIRECTORY=target/generated-sources/aqSchema
sedArg=" -i -r "
if [[ `uname` == "Darwin" ]]; then
sedArg=" -i.bak -E "
fi

sed $sedArg 's/type="tns:StatisticalDateTimeOffset"/type="xs:dateTime"/g' $XSD_DIRECTORY/*.xsd
sed $sedArg 's/type="q[^:]+:DateTimeOffset"/type="xs:dateTime"/g' $XSD_DIRECTORY/*.xsd
sed $sedArg 's/type="q[^:]+:ArrayOfstring"/type="xs:string" maxOccurs="unbounded"/g' $XSD_DIRECTORY/*.xsd
sed $sedArg 's/type="q[^:]+:ArrayOfdouble"/type="xs:double" maxOccurs="unbounded"/g' $XSD_DIRECTORY/*.xsd
sed $sedArg 's/type="q[^:]+:ArrayOfNullableOfdouble"/type="xs:double" maxOccurs="unbounded"/g' $XSD_DIRECTORY/*.xsd
sed $sedArg 's/type="q([^:]+):ArrayOf([^"]+)"/type="q\1:\2" maxOccurs="unbounded"/g' $XSD_DIRECTORY/*.xsd
sed $sedArg 's/type="q([^:]+):KeyValueOfstringSwagger([^"]+)"/type="q\1:ArrayOfKeyValueOfstringSwagger\2" maxOccurs="unbounded"/g' $XSD_DIRECTORY/*.xsd #the previous line is abit heavy handed and broke ArrayOfKeyValueOfstringSwagger*, this line reverts it
sed $sedArg 's/type="q([^:]+):KeyValueOfstringstring" maxOccurs="unbounded"/type="q\1:ArrayOfKeyValueOfstringstring"/g' $XSD_DIRECTORY/*.xsd
sed $sedArg 's/type="q([^:]+):KeyValueOfstringanyType" maxOccurs="unbounded"/type="q\1:ArrayOfKeyValueOfstringanyType"/g' $XSD_DIRECTORY/*.xsd
sed $sedArg 's/type="q[^:]+:guid"/type="xs:string" /g' $XSD_DIRECTORY/*.xsd
sed $sedArg 's/minOccurs="0" name="([^"]+)" nillable="true" type="tns:ArrayOf([^"]+)"/minOccurs="0" name="\1" nillable="true" type="tns:\2" maxOccurs="unbounded"/g' $XSD_DIRECTORY/*.xsd
sed $sedArg 's/AuthenticateResponse/AuthenticateResponseObj/g' $XSD_DIRECTORY/*.xsd