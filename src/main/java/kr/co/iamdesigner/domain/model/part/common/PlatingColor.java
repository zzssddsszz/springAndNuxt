package kr.co.iamdesigner.domain.model.part.common;

public enum PlatingColor {
    NONE("NONE","무도금"),PINK("PINK","핑크골드"),WHITE("WHITE","화이트골드"),
    GOLD14K("GOLD14K","14K골드"),BLACK("BLACK","흑도금"),
    ANTIQUE_BRIGHTLY("ANTIQUE_BRIGHTLY","유화유광"),ANTIQUE_SCRATCH("ANTIQUE_SCRATCH","유화무꽝");

    String typeCode;
    String typeName;

    PlatingColor(String typeCode, String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public String getTypeName() {
        return typeName;
    }


}
