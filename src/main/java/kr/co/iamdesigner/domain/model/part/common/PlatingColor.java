package kr.co.iamdesigner.domain.model.part.common;

import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum PlatingColor {
    NONE("NONE","무도금"),PINK("PINK","핑크골드"),WHITE("WHITE","화이트골드"),
    GOLD14K("GOLD14K","14K골드"),BLACK("BLACK","흑도금"),
    ANTIQUE_BRIGHTLY("ANTIQUE_BRIGHTLY","유화유광"),ANTIQUE_SCRATCH("ANTIQUE_SCRATCH","유화무꽝");

    String typeCode;
    String typeName;
    private static List<PlatingColor> allList;

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

    public boolean checkType(String type){
        return EnumUtils.isValidEnum(PlatingColor.class,type);
    }

    public static List<PlatingColor> getAllList() {
        if (ObjectUtils.anyNull(allList)){
            allList = new ArrayList<>(Arrays.asList(NONE, PINK, WHITE, GOLD14K, BLACK, ANTIQUE_BRIGHTLY, ANTIQUE_SCRATCH));
        }
        return allList;
    }
}
