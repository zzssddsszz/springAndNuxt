package kr.co.iamdesigner.domain.model.part.common;

public enum Material {
    SILVER("SILVER","실버"),GOLD24K("GOLD24K","순금"),GOLD14K("GOLD14K","14K골드"),GOLD18K("GOLD18K","18K골드");

    String typeCode;
    String typeName;

    Material(String typeCode,String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    public String getTypeCode(){return typeCode;}

    public String getTypeName(){return typeName;}
}
