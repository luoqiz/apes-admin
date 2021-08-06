package top.luoqiz.file.generator.create.utils;


/**
 * @author luoqiz
 */
public class DataDeal {

//    public static TableInfoModel JtableToData(TableInfoModel dm) {
//        dm = getConfigFormProperties(dm);
//        File directory = new File("");// 设定为当前文件夹
////        if (dm.isTableExists()) {
////            // 设置生成文件所在包名
////            dm.setEntityPackageName(dm.getEntityPackageName() + "." + getCamelStr(dm.getTableName().toLowerCase()));
////        }
////
////        dm.setProjectPath(directory.getAbsolutePath().replaceAll("\\.", "/"));
////        // 实体包路径
////        dm.setEntityPackagePath(dm.getEntityPackageName().replaceAll("\\.", "/"));
////        // 实体类名称
////        dm.setEntityName(getCamelStr(initcap(dm.getTableName().toLowerCase())));
////        // 获取项目路径
////        /*
////         * // String projectPath = System.getProperty("user.dir"); // String pagePath =
////         * projectPath + "/WebContent/";
////         */
////        // 实体类包名
////        System.out.println("实体类包名--->" + dm.getEntityPackageName());
////
////        // 实体类完整路径（no include subs）
////        File fullPackagePathTest = new File(directory.getAbsolutePath() + "/src/main/java");
////        if (fullPackagePathTest.exists()) {
////            dm.setFullPackagePath(
////                    directory.getAbsolutePath() + "/src/main/java/" + dm.getEntityPackageName().replaceAll("\\.", "/"));
////        } else {
////            dm.setFullPackagePath(
////                    directory.getAbsolutePath() + "/src/" + dm.getEntityPackageName().replaceAll("\\.", "/"));
////
////        }
////        File webRoot = new File(directory.getAbsolutePath() + "/WebRoot");
////        // 实体类对应页面完整路径
////        if (webRoot.exists() && webRoot.isDirectory()) {
////            dm.setPagePath(directory.getAbsolutePath() + "/WebRoot/" + dm.getEntityPackagePath());
////        } else {
////            dm.setPagePath(directory.getAbsolutePath() + "/WebContent/" + dm.getEntityPackagePath());
////        }
//
//        @SuppressWarnings("unchecked")
//        ArrayList<TableColumnInfoModel> jtableColumnInfoModels = (ArrayList<TableColumnInfoModel>) dm.getArrayList();
//
//        for (TableColumnInfoModel row : jtableColumnInfoModels) {
//            String javaColumnName = getCamelStr(row.getDbColumnName());
//            row.setJavaColumnName(javaColumnName);
//
//            row.setDbColumnType(sqlType2MybatisType(row.getDbColumnType()));
//            // 将数据库数据类型转为java对应的数据类型
//            String colType = dbType2JavaType(row.getDbColumnType());
//            // System.out.println(row.getSqlColumnType());
//            row.setJavaColumnType(colType);
//
//            if (row.isPrimaryKey()) {
//                dm.setSqlTablePK(row.getDbColumnName());
//                dm.setJavaTablePK(javaColumnName);
//            }
//
////            if (colType.equalsIgnoreCase("date")) {
////                dm.setImportUtil(true);
////            } else if (colType.equalsIgnoreCase("Blob") || colType.equalsIgnoreCase("Clob")) {
////                dm.setImportSql(true);
////            }
//        }
//        dm.setColSize(jtableColumnInfoModels.size());
//        dm.setArrayList(jtableColumnInfoModels);
//        return dm;
//    }

    public static String dbType2JavaType(String sqlType) {
        sqlType = sqlType.trim();
        if (sqlType.equalsIgnoreCase("bit")) {
            return "Boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "byte";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "short";
        } else if (sqlType.equalsIgnoreCase("int") || sqlType.equalsIgnoreCase("integer")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "Long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "Float";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("double")) {
            return "Double";
        } else if (sqlType.equalsIgnoreCase("money") || sqlType.equalsIgnoreCase("smallmoney")) {
            return "Double";
        } else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("date")
                || sqlType.equalsIgnoreCase("TIMESTAMP")) {
            return "LocalDateTime";
        } else if (sqlType.equalsIgnoreCase("date")) {
            return "LocalDate";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blob";
        } else if (sqlType.equalsIgnoreCase("text")) {
            return "Clob";
        }
        return "String";
    }

    public static String sqlType2MybatisType(String sqlType) {
        sqlType = sqlType.trim();
        if (sqlType.equalsIgnoreCase("bit")) {
            return "BIT";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "byte";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "short";
        } else if (sqlType.equalsIgnoreCase("int") || sqlType.equalsIgnoreCase("integer")) {
            return "INTEGER";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "float";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("double")) {
            return "DOUBLE";
        } else if (sqlType.equalsIgnoreCase("money") || sqlType.equalsIgnoreCase("smallmoney")) {
            return "DOUBLE";
        } else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("date")
                || sqlType.equalsIgnoreCase("TIMESTAMP")) {
            return "TIMESTAMP";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "BLOB";
        } else if (sqlType.equalsIgnoreCase("clob")) {
            return "Clob";
        } else if (sqlType.equalsIgnoreCase("text")) {
            return "LONGVARCHAR";
        }
        return "VARCHAR";
    }

    /**
     * 把输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    public static String capital(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return getCamelStr(new String(ch));
    }

    /**
     * 把输入字符串的首字母改成小写
     *
     * @param str
     * @return
     */
    public static String lowercase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'A' && ch[0] <= 'Z') {
            ch[0] = (char) (ch[0] + 32);
        }
        return getCamelStr(new String(ch));
    }

    /**
     * 例：user_name --> userName
     *
     * @param s
     * @return
     */
    public static String getCamelStr(String s) {
        while (s.indexOf("_") > 0) {
            int index = s.indexOf("_");
            // System.out.println(s.substring(index+1, index+2).toUpperCase());
            s = s.substring(0, index) + s.substring(index + 1, index + 2).toUpperCase() + s.substring(index + 2);
        }
        return s;
    }

    // 从文件中加载配置信息
//    public static TableInfoModel getConfigFormProperties(TableInfoModel dm) {
//        InputStream inStream = ClassLoader.getSystemResourceAsStream("autoCreateConfig.properties");
//        Properties prop = new Properties();
//        try {
//            prop.load(inStream);
//        } catch (IOException e) {
//            System.out.println("未在resources目录中读取到autoCreateConfig.properties配置文件");
//            e.printStackTrace();
//        }
//        dm.setAuthor(prop.getProperty("user.author"));
//        dm.setTableExists(Boolean.parseBoolean(prop.getProperty("tableExsits")));
//        try {
//            prop.clear();
//            prop.clone();
//            inStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return dm;
//    }

    public static String dbType2MybatisType(String dbColumnType) {
        return sqlType2MybatisType(dbColumnType);
    }

    public static String dbType2TsType(String sqlType) {
        sqlType = sqlType.trim();
        if (sqlType.equalsIgnoreCase("bit")) {
            return "boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "number";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "number";
        } else if (sqlType.equalsIgnoreCase("int") || sqlType.equalsIgnoreCase("integer")) {
            return "number";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "number";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "number";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("double")) {
            return "number";
        } else if (sqlType.equalsIgnoreCase("money") || sqlType.equalsIgnoreCase("smallmoney")) {
            return "number";
        } else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("date")
                || sqlType.equalsIgnoreCase("TIMESTAMP")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("date")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "string";
        } else if (sqlType.equalsIgnoreCase("text")) {
            return "string";
        }
        return "string";
    }
}
