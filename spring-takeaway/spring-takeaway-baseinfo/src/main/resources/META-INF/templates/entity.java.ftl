package ${packageName};

import java.io.Serializable;
<#list importList as import>
import ${import};
</#list>
<#if entityLombokModel>
import lombok.Data;
</#if>

  /**
    * <p>
    * ${className}
    * </p>
    *
    * @author ${author}
    * @since ${.now?string["yyyy-MM-dd HH:mm:ss"]}
    */
<#if entityLombokModel>
@Data
</#if>
public class ${className} implements Serializable {

    private static final long serialVersionUID = 1L;
<#list javaFieldInfoList as javaFieldInfo>

    /**
    * ${javaFieldInfo.fieldName}
    */
    private ${javaFieldInfo.fieldType} ${javaFieldInfo.fieldName};
</#list>
<#if !entityLombokModel>
    <#list javaFieldInfoList as javaFieldInfo>

    public ${javaFieldInfo.fieldType} get${javaFieldInfo.fieldName?cap_first}() {
        return this.${javaFieldInfo.fieldName};
    }

    public void set${javaFieldInfo.fieldName?cap_first}(${javaFieldInfo.fieldType} ${javaFieldInfo.fieldName}) {
        this.${javaFieldInfo.fieldName} = ${javaFieldInfo.fieldName};
    }
    </#list>
</#if>


}
