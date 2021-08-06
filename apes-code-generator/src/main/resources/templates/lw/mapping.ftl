<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${entityPackageName}.mapper.${entityName}Mapper">
    <resultMap id="BaseResultMap" type="${entityPackageName}.model.${entityName}">
        <#list arrayList as row>
            <#if javaTablePK==row["javaColumnName"]>
      <id column="${row["dbColumnName"]}" jdbcType="${row["dbColumnType"]?upper_case}" property="${row["javaColumnName"]}" />
            <#else>
      <result column="${row["dbColumnName"]}" jdbcType="${row["dbColumnType"]?upper_case}" property="${row["javaColumnName"]}" />
            </#if>
        </#list>
    </resultMap>
    <sql id="Base_Column_List">
        <#assign x = 0/>
        <#list arrayList as row>
            <#assign x = x+1/>
            <#if x==colSize>
                ${row["dbColumnName"]}
            <#else>
                ${row["dbColumnName"]},
            </#if>
        </#list>
    </sql>
    <sql id="Base_Where">
    <where>
        <#list arrayList as row>
            <#if row["searchWay"]??>
            <#if row["searchWay"]=="=">
                <if test="${row["javaColumnName"]} != null">
                ${row["otherOper"]} ${row["dbColumnName"]} = ${r'#{'}${row["javaColumnName"]},jdbcType= ${row["dbColumnType"]}}
                </if>
            </#if>
            <#if row["searchWay"]==">">
                <if test="${row["javaColumnName"]}LT != null">
                ${row["otherOper"]} ${row["dbColumnName"]} &gt; ${r'#{'}${row["javaColumnName"]}LT,jdbcType= ${row["dbColumnType"]}}
                </if>
            </#if>
            <#if row["searchWay"]=="<">
                <if test="${row["javaColumnName"]}GT != null">
                ${row["otherOper"]} ${row["dbColumnName"]} &lt; ${r'#{'}${row["javaColumnName"]}GT,jdbcType= ${row["dbColumnType"]}}
                </if>
            </#if>
            <#if row["searchWay"]=="<>">
                <if test="${row["javaColumnName"]}LT != null">
                ${row["otherOper"]} ${row["dbColumnName"]} &gt; ${r'#{'}${row["javaColumnName"]}LT,jdbcType= ${row["dbColumnType"]}}
                </if>
                 <if test="${row["javaColumnName"]}GT != null">
                ${row["otherOper"]} ${row["dbColumnName"]} &lt; ${r'#{'}${row["javaColumnName"]}GT,jdbcType= ${row["dbColumnType"]}}
                </if>
            </#if>
            <#if row["searchWay"]=="like">
                <if test="${row["javaColumnName"]}Like != null ">
                    <#if dbType=="Mysql">
                        ${row["otherOper"]} ${row["dbColumnName"]} like CONCAT('%',${r'#{'}${row["javaColumnName"]}Like,jdbcType= ${row["dbColumnType"]}},'%')
                    <#elseif dbType="Mssql">
                        ${row["otherOper"]} ${row["dbColumnName"]} like '%'+${r'#{'}${row["javaColumnName"]}Like,jdbcType= ${row["dbColumnType"]}}+'%'
                    <#else>
                        ${row["otherOper"]} ${row["dbColumnName"]} like '%'||${r'#{'}${row["javaColumnName"]}Like,jdbcType= ${row["dbColumnType"]}}||'%'
                    </#if>
                </if>
            </#if>
            </#if>
        </#list>
        <if test="sortName != null">
                order by ${r'#{'}sortName} ${r'#{'}sortOrder}
         </if>
    </where>
    </sql>

  <insert id="save${entityName}" parameterType="${entityPackageName}.model.${entityName}">
      insert into ${tableName} (
        <#assign x = 0/>
        <#list arrayList as row>
            <#assign x = x+1/>
            <#if x==colSize>
                ${row["dbColumnName"]}
            <#else>
                ${row["dbColumnName"]},
            </#if>
        </#list>
      )
      values (
        <#assign x = 0/>
        <#list arrayList as row>
            <#assign x = x+1/>
            <#if x==colSize>
                ${r'#{'} ${row["javaColumnName"]},jdbcType=${row["dbColumnType"]}}
            <#else>
                ${r'#{'} ${row["javaColumnName"]},jdbcType=${row["dbColumnType"]}},
            </#if>
        </#list>
        )
  </insert>
  
  <insert id="save${entityName}Selective" parameterType="${entityPackageName}.model.${entityName}">
      insert into ${tableName} 
      <trim prefix="(" suffix=")" suffixOverrides=",">
       <#assign x = 0/>
        <#list arrayList as row>
            <#assign x = x+1/>
            <#if x==colSize>
                <if test="${row["javaColumnName"]} != null ">
                ${row["dbColumnName"]}
               </if>
            <#else>
               <if test="${row["javaColumnName"]} != null">
                ${row["dbColumnName"]},
               </if>
            </#if>
        </#list>
      </trim>
    <trim prefix="values (" suffix=")" suffixOverrides=",">
        <#assign x = 0/>
        <#list arrayList as row>
            <#assign x = x+1/>
            <#if x==colSize>
                <if test="${row["javaColumnName"]} != null">
                    ${r'#{'} ${row["javaColumnName"]},jdbcType=${row["dbColumnType"]}}
                 </if>
            <#else>
                <if test="${row["javaColumnName"]} != null">
                    ${r'#{'} ${row["javaColumnName"]},jdbcType=${row["dbColumnType"]}},
                </if>   
            </#if>
        </#list>
  </trim>
  </insert>

  <insert id="saveList${entityName}" parameterType="${entityPackageName}.model.${entityName}">
      insert into ${tableName} (
       <#assign x = 0/>
        <#list arrayList as row>
            <#assign x = x+1/>
            <#if x==colSize>
               ${row["dbColumnName"]}
            <#else>
                ${row["dbColumnName"]},
            </#if>
        </#list>
        )
        values
        <foreach item="item" index="index" collection="array" open="" separator="or" close="">
           <trim prefix="(" suffix=")" suffixOverrides=",">
            <#assign x = 0/>
            <#list arrayList as row>
                <#assign x = x+1/>
                <#if x==colSize>
                        ${r'#{'}item.${row["javaColumnName"]},jdbcType=${row["dbColumnType"]}}
                <#else>
                        ${r'#{'}item.${row["javaColumnName"]},jdbcType=${row["dbColumnType"]}},
                </#if>
            </#list>
            </trim>
        </foreach>
        
  </insert>

<select id="count${entityName}Condition" parameterType="${entityPackageName}.model.${entityName}Condition" resultType="int">
    select count(1) from ${tableName}
    <include refid="Base_Where" />
</select>

<select id="list${entityName}Condition" parameterType="${entityPackageName}.model.${entityName}Condition" resultMap="BaseResultMap">
    select 
    <include refid="Base_Column_List" />
     from ${tableName}
    <include refid="Base_Where" />
    LIMIT ${r'#{'}startRowNumber},${r'#{'}pageSize}
</select>

<#if javaTablePK??>

<delete id="remove${entityName}ByPrimaryKey" parameterType="java.lang.String">
delete from ${tableName} where ${sqlTablePK} = ${r'#{'}${javaTablePK},jdbcType=VARCHAR} 
</delete>

<delete id="removeList${entityName}ByPrimaryKey">
delete from ${tableName} where 
        <foreach item="item" index="index" collection="array" open="" separator="or" close="">
            ${sqlTablePK} = ${r'#{'}item} 
        </foreach>

</delete>

<update id="update${entityName}ByPrimaryKey" parameterType="${entityPackageName}.model.${entityName}">
    update ${tableName} 
    	
     <#assign x = 0/>
        <#list arrayList as row>
            <#assign x = x+1/>
            <#if javaTablePK!=row["javaColumnName"]>
	     		<#if x==colSize>
	                 ${row["dbColumnName"]} = ${r'#{'} ${row["javaColumnName"]},jdbcType=${row["dbColumnType"]}}
	            <#else>
	                 ${row["dbColumnName"]} = ${r'#{'} ${row["javaColumnName"]},jdbcType=${row["dbColumnType"]}},
	            </#if>
           </#if>
        </#list>
       
        where ${sqlTablePK} = ${r'#{'}${javaTablePK},jdbcType=VARCHAR}
</update>


<update id="update${entityName}ByPrimaryKeySelective" parameterType="${entityPackageName}.model.${entityName}">
    update ${tableName} <set>
     <#assign x = 0/>
        <#list arrayList as row>
            <#assign x = x+1/>
            <#if javaTablePK!=row["javaColumnName"]>
	            <if test="${row["javaColumnName"]} != null">
	            ${row["dbColumnName"]} = ${r'#{'} ${row["javaColumnName"]},jdbcType=${row["dbColumnType"]}},
	            </if>
            </#if>
        </#list>
        </set>
        where ${sqlTablePK} = ${r'#{'}${javaTablePK},jdbcType=VARCHAR}
</update>


    <select id="get${entityName}ByPrimaryKey" parameterType="java.lang.String" resultMap="BaseResultMap">
    select 
    <include refid="Base_Column_List" />
    from ${tableName}
    where ${sqlTablePK} = ${r'#{'}${javaTablePK},jdbcType=VARCHAR}
  </select>
 </#if>
</mapper>