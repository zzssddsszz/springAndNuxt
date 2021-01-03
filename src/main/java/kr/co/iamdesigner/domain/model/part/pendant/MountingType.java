package kr.co.iamdesigner.domain.model.part.pendant;

public enum MountingType {
    SINGLE("SINGLE","1고리"),DOUBLE("DOUBLE","2고리"),SINGLE_HOLE("SINGLE_HOLE","1오링"),
    DOUBLE_HOLE("DOUBLE_HOLE","2오링"),PASSING("PASSING","통과");

    String typeCode;
    String typeName;

    MountingType(String typeCode,String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    public String getTypeCode(){return typeCode;}

    public String getTypeName(){return typeName;}

}
