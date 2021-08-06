package top.luoqiz.file.generator.ui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import top.luoqiz.file.generator.create.model.*;
import top.luoqiz.file.generator.create.utils.DBSqlDeal;
import top.luoqiz.file.generator.create.utils.DataDeal;
import top.luoqiz.file.generator.create.utils.GeneratorUtil;
import top.luoqiz.file.generator.event.FilePathChangeEventHandler;
import top.luoqiz.file.generator.widget.CellFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * @author luoqiz
 */
public class UiController {

    @FXML
    private ComboBox dataType;
    @FXML
    private TextField dataAddr;
    @FXML
    private TextField dataName;
    @FXML
    private TextField dataPort;
    @FXML
    private TextField dataUserName;
    @FXML
    private TextField dataUserPwd;
    @FXML
    private TextField authorText;
    @FXML
    private TextField versionText;
    @FXML
    private TextField packageText;
    @FXML
    private TextField tabPrefixText;
    @FXML
    private TextField swagger3Select;
    @FXML
    private TextField pagePathText;

    @FXML
    private ComboBox tablesList;

    @FXML
    private TableView tbColumnInfos;

    @FXML
    private ComboBox fileListComboBox;

    @FXML
    private ComboBox projectModuleComboBox;

    @FXML
    private File[] templates;

//    @FXML
//    private File template;

    @FXML
    private File[] modules;

//    @FXML
//    private File module;

    @FXML
    private TableView tableView;

    private GeneratorConfigModel config = FilePathChangeEventHandler.config;

    private GeneratorValueChangeModel generatorValueChangeModel = new GeneratorValueChangeModel();

    @FXML
    public void initialize() {
        // 设置数据库类型和端口号
        dataType.getItems().addAll("Mysql", "Mssql", "Oracle");
        dataType.setValue("Mysql");
        dataType.setOnAction(event -> {
            String dataTypeValue = dataType.getValue().toString();
            if ("Mssql".equalsIgnoreCase(dataTypeValue)) {
                dataPort.setText("1433");
                dataUserName.setText("sa");
            } else if ("mysql".equalsIgnoreCase(dataTypeValue)) {
                dataPort.setText("3306");
                dataUserName.setText("root");
            } else {
                dataPort.setText("1521");
                dataUserName.setText("sys");
            }
        });

        // 设置文件生成列表
        URL url = ClassLoader.getSystemResource("templates");
        System.out.println("项目资源地址：" + url.getPath());
        File file = new File(url.getPath());
        templates = file.listFiles();
        for (File s : templates) {
            fileListComboBox.getItems().add(s.getName());
        }

        // 列出可生成的文件
        fileListComboBox.setOnAction((event) -> {
            Object selectedItem = fileListComboBox.getSelectionModel().getSelectedItem();
            for (File s : templates) {
                if (s.getName().equals(selectedItem.toString())) {
                    generatorValueChangeModel.setTemplate(s);
//                    template = s;
                    createFileTable();
                    config.setTemplatePath(s.getAbsolutePath());
                    System.out.println("设置模板路径：" + s.getAbsolutePath());
                    break;
                }
            }
        });

        // 设置文件生成模块列表
        String projectPath = System.getProperty("user.dir");
        System.out.println("项目地址：" + url.getPath());
        File projectFile = new File(projectPath);
        modules = projectFile.listFiles();
        for (File s : modules) {
            if (s.isDirectory() && Arrays.stream(s.list()).filter(temp -> temp.contains("src")).findFirst().isPresent()) {
                projectModuleComboBox.getItems().add(s.getName());
            }
        }
        // 列出项目下的子模块
        projectModuleComboBox.setOnAction(event -> {
            Object selectedItem = projectModuleComboBox.getSelectionModel().getSelectedItem();
            for (File s : modules) {
                if (s.getName().equals(selectedItem.toString())) {
//                    module = s;
                    generatorValueChangeModel.setModule(s);
                    createFileTable();
                    break;
                }
            }
        });

        // 更换表的事件监听
        tablesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                setTableClassName(newValue.toString().split(":")[0].trim());
                config.getTableInfo().setComment(newValue.toString().split(":")[1].trim());
                createFileTable();
            }
        });

        // 设置生成的文件列表
        tableView.setEditable(true);
        fileTableColumn();

    }

    /**
     * 生成 文件列表表格的列信息
     */
    private void fileTableColumn() {
        TableColumn<GenerateFileModel, Boolean> col1 = new TableColumn<>("是否生成");
        col1.setCellValueFactory(new PropertyValueFactory<>("created"));
        col1.setCellFactory(
                CellFactory.tableCheckBoxColumn(index -> {
                    final GenerateFileModel g = (GenerateFileModel) tableView.getItems().get(index);
                    ObservableValue<Boolean> ret =
                            new SimpleBooleanProperty(g, "selected", g.getCreated());
                    ret.addListener((observable, oldValue, newValue) -> g.setCreated(newValue));
                    return ret;
                }));

        tableView.getColumns().add(col1);

        TableColumn<GenerateFileModel, String> col2 = new TableColumn<>("模板名称");
        col2.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        col2.setPrefWidth(150.0);
        tableView.getColumns().add(col2);


        TableColumn<GenerateFileModel, String> col3 = new TableColumn<>("包名");
        col3.setEditable(true);
        col3.setCellValueFactory(new PropertyValueFactory<>("packagePath"));
        col3.setCellFactory(TextFieldTableCell.forTableColumn());
        col3.setPrefWidth(200.0);
        col3.setOnEditCommit(
                (TableColumn.CellEditEvent<GenerateFileModel, String> t) -> {
                    GenerateFileModel rowData = t.getTableView().getItems().get(t.getTablePosition().getRow());
                    rowData.setPackagePath(t.getNewValue());
                    setFilePath(rowData);
                    tableView.refresh();
                });
        tableView.getColumns().add(col3);

        TableColumn<GenerateFileModel, String> col4 = new TableColumn<>("生成文件路径");
        col4.setPrefWidth(450.0);
        col4.setCellFactory(param -> new TextFieldTableCell());
        col4.setCellValueFactory(new PropertyValueFactory<>("finalPath"));
        col4.setCellFactory(TextFieldTableCell.forTableColumn());
        col4.setOnEditCommit(
                (TableColumn.CellEditEvent<GenerateFileModel, String> t) -> {
                    (t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setFinalPath(t.getNewValue());
                });
        tableView.getColumns().add(col4);
    }

    /**
     * 生成 文件列表表格的 数据
     */
    private void createFileTable() {
        tableView.getItems().clear();
        List<GenerateFileModel> fileList = new ArrayList<>();
        if (generatorValueChangeModel.getTemplate() == null) {
            return;
        }

        File[] temps = generatorValueChangeModel.getTemplate().listFiles();
        for (File tmp : temps) {
            GenerateFileModel item = new GenerateFileModel();
            item.setCreated(true);
            item.setFileName(tmp.getName());
            item.setPackagePath(config.getBasePackage() + "." + tmp.getName().split("\\.")[0]);
            setFilePath(item);
            fileList.add(item);
        }
        ObservableList<GenerateFileModel> fliest = FXCollections.observableList(fileList);
        tableView.setItems(fliest);
    }

    private void setFilePath(GenerateFileModel item) {
        String path = null;
        // 获取模块名称，后续加入到生成路径中
        String modulePath = "";
        File module = generatorValueChangeModel.getModule();
        if (module != null && module.exists() && module.isDirectory()) {
            modulePath = "/" + module.getName();
        }

        if ("java".equalsIgnoreCase(item.getSuffix())) {
            path = config.getProjectPath() + modulePath
                    + "/src/main/java/"
                    + item.getPackagePath().replaceAll("\\.", "/")
                    + "/"
                    + config.getTableInfo().getClassName()
                    + item.getSummary() + "." + item.getSuffix();
        } else if ("xml".equalsIgnoreCase(item.getSuffix())) {
            path = config.getProjectPath() + modulePath
                    + "/src/main/resources/"
                    + "mapper/"
                    + config.getTableInfo().getClassName()
                    + item.getSummary() + "." + item.getSuffix();
        } else if ("ts".equalsIgnoreCase(item.getSuffix())) {
            path = config.getProjectPath() + modulePath
                    + "/src/main/resources/"
                    + "front/"
                    + config.getTableInfo().getClassName()
                    + "/"
                    + item.getSummary().toLowerCase() + "." + item.getSuffix();
        } else if ("vue".equalsIgnoreCase(item.getSuffix())) {
            path = config.getProjectPath() + modulePath
                    + "/src/main/resources/"
                    + "front/"
                    + config.getTableInfo().getClassName()
                    + "/components/"
                    + item.getSummary() + "." + item.getSuffix();
        } else {
            path = config.getProjectPath() + modulePath
                    + "/src/main/resources/"
                    + config.getTableInfo().getClassName()
                    + "/"
                    + item.getSummary() + "." + item.getSuffix();
        }
        item.setFinalPath(path);
    }

    /**
     * 保存基本配置信息
     */
    public void baseConfigSaveEvent() {
        Properties fileConfigProperties = readConfigProperties();

        // 设置配置信息
        config.setAuthor(authorText.getText());
        config.setVersion(versionText.getText());
        config.setTablePrefix(tabPrefixText.getText());
        config.setTableExists("true".equalsIgnoreCase(fileConfigProperties.getProperty("tableExists")));

        config.setProjectPath(fileConfigProperties.getProperty("projectPath"));
        //从文件中获包名or获取所在包路径
        config.setBasePackage(packageText.getText());
        config.setPagePath(pagePathText.getText());
        config.setSwagger3("true".equalsIgnoreCase(fileConfigProperties.getProperty("swagger3")));
    }

    /**
     * 全选
     */
    public void doSelect() {
        for (Object item : tableView.getItems()) {
            GenerateFileModel gfl = (GenerateFileModel) item;
            gfl.setCreated(true);
        }
        tableView.refresh();
    }

    /**
     * 反选
     */
    public void doUnSelect() {
        for (Object item : tableView.getItems()) {
            GenerateFileModel gfl = (GenerateFileModel) item;
            gfl.setCreated(!gfl.getCreated());
        }
        tableView.refresh();
    }

    /**
     * 开始生成文件
     *
     * @param event
     */
    @FXML
    protected void generatorFile(ActionEvent event) {
        List<GenerateFileModel> fileInfo = new ArrayList<>();
        ObservableList<GenerateFileModel> items = tableView.getItems();
        for (GenerateFileModel generateFileName : items) {
            if (generateFileName.getCreated()) {
                fileInfo.add(generateFileName);
            }
        }
        config.setFileList(fileInfo);

        ArrayList<TableColumnInfoModel> list = new ArrayList<>();
        for (Object item : tbColumnInfos.getItems()) {
            list.add((TableColumnInfoModel) item);
        }

        TableInfoModel tableInfoModel = config.getTableInfo();
        tableInfoModel.setColInfoList(list);
        tableInfoModel.setColSize(list.size());

        GeneratorUtil.generate(config);
    }

    /**
     * 设置表的类名（去除表前缀）
     */
    private void setTableClassName(String tableName) {
        TableInfoModel tableInfo = config.getTableInfo();
        tableInfo.setTableName(tableName);
        String prefix = "^" + config.getTablePrefix();
        String preClass = tableName.replaceAll(prefix, "").replaceAll(prefix + "_", "");
        tableInfo.setClassName(DataDeal.capital(preClass));
        tableInfo.setClassNameFirstLower(DataDeal.lowercase(preClass));
    }

    @FXML
    public void resetConfig() {
        dataAddr.setText("");
        dataName.setText("");
        dataPort.setText("");
        dataUserName.setText("");
        dataUserPwd.setText("");
    }

    @FXML
    public void baseConfigClearEvent() {
        authorText.setText("");
        versionText.setText("");
        packageText.setText("");
        tabPrefixText.setText("");
        swagger3Select.setText("");
        pagePathText.setText("");
    }

    /**
     * 读取配置文件
     *
     * @return
     */
    public Properties readConfigProperties() {
        InputStream inStream = ClassLoader.getSystemResourceAsStream("autoCreateConfig.properties");
        Properties prop = new Properties();
        try {
            prop.load(inStream);
        } catch (IOException e) {
            System.out.println("未在resources目录中读取到autoCreateConfig.properties配置文件");
            e.printStackTrace();
        } finally {
            try {
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return prop;
    }

    /**
     * 从配置文件中读取并配置界面上的数据库信息
     */
    @FXML
    public void setDbConfig() {
        Properties prop = readConfigProperties();
        dataType.setValue(prop.getProperty("dataType"));
        dataAddr.setText(prop.getProperty("dataAddr"));
        dataName.setText(prop.getProperty("dataName"));
        dataPort.setText(prop.getProperty("dataPort"));
        dataUserName.setText(prop.getProperty("dataUserName"));
        dataUserPwd.setText(prop.getProperty("dataUserPWD"));
    }

    /**
     * 从配置文件中读取并配置界面上的数据库信息
     */
    @FXML
    public void baseConfigReadConfigEvent() {
        Properties prop = readConfigProperties();
        authorText.setText(prop.getProperty("author"));
        versionText.setText(prop.getProperty("version"));
        packageText.setText(prop.getProperty("basePackage"));
        tabPrefixText.setText(prop.getProperty("tablePrefix"));
        swagger3Select.setText(prop.getProperty("swagger3"));
        pagePathText.setText(prop.getProperty("pagePath"));
    }

    /**
     * 加载表
     */
    @FXML
    private void getTables() {
        ConnectModel connectModel = getConnectModel();
        ArrayList<Object> dbTables = DBSqlDeal.executeSql(connectModel.getTableQuerySql(), connectModel);
        tablesList.getItems().clear();
        if (dbTables.size() > 0) {
            for (int i = 0; i < dbTables.size(); i++) {
                Object[] objects = (Object[]) dbTables.get(i);
                tablesList.getItems().add(objects[0].toString() + " : " + objects[1].toString());
            }
        }
    }

    /**
     * 设置链接数据库属性
     *
     * @return
     */
    public ConnectModel getConnectModel() {
        ConnectModel connectModel = new ConnectModel();
        String dbType = dataType.getValue().toString();
        connectModel.setDbType(dbType);
        connectModel.setDbAddr(dataAddr.getText());
        connectModel.setDbPort(dataPort.getText());
        connectModel.setDbName(dataName.getText());
        connectModel.setDbUserName(dataUserName.getText());
        connectModel.setDbUserPWD(dataUserPwd.getText());
        return connectModel;
    }


    /**
     * 加载列信息
     */
    @FXML
    public void getTablesColumn() {
        ConnectModel connectModel = getConnectModel();
        connectModel.setTableName(config.getTableInfo().getTableName());
        ArrayList<Object> dataColumns = DBSqlDeal.executeSql(connectModel.getColumnQuerySql(), connectModel);
        List<TableColumnInfoModel> tableDataList = new ArrayList<>();
        dataColumns.forEach(columns -> {
            Object[] objects = (Object[]) columns;

            String columnLengthStr = Optional.ofNullable(objects[5]).map(String::valueOf).orElse("0");
            TableColumnInfoModel tableColumnInfoModel = new TableColumnInfoModel(Integer.parseInt(objects[0].toString()), objects[1].toString(),
                    objects[2].toString(), "YES".equalsIgnoreCase(objects[3].toString()), "YES".equalsIgnoreCase(objects[4].toString()),
                    Integer.parseInt(columnLengthStr),
                    "YES".equalsIgnoreCase(objects[6].toString()),
                    Optional.ofNullable(objects[7]).map(String::valueOf).orElse(null),
                    Optional.ofNullable(objects[8]).map(String::valueOf).orElse(null),
                    "", "and", true);
            tableDataList.add(tableColumnInfoModel);
        });
        tbColumnInfos.setEditable(true);
        ObservableList<TableColumn<TableColumnInfoModel, Object>> cls = tbColumnInfos.getColumns();
        cls.get(0).setCellValueFactory(new PropertyValueFactory<>("num"));
        cls.get(1).setCellValueFactory(new PropertyValueFactory<>("dbColumnName"));
        cls.get(2).setCellValueFactory(new PropertyValueFactory<>("dbColumnType"));
        cls.get(3).setCellValueFactory(new PropertyValueFactory<>("indexKey"));
        cls.get(4).setCellValueFactory(new PropertyValueFactory<>("primaryKey"));
        cls.get(5).setCellValueFactory(new PropertyValueFactory<>("columnLength"));
        cls.get(6).setCellValueFactory(new PropertyValueFactory<>("nullValue"));
        cls.get(7).setCellValueFactory(new PropertyValueFactory<>("defaultValue"));
        cls.get(8).setCellValueFactory(new PropertyValueFactory<>("comment"));

        List<String> searchWay = new ArrayList<>();
        searchWay.add("");
        searchWay.add("=");
        searchWay.add(">");
        searchWay.add("<");
        searchWay.add("<>");
        searchWay.add("like");
        cls.get(9).setCellValueFactory(new PropertyValueFactory<>("searchWay"));
        cls.get(9).setEditable(true);
        cls.get(9).setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(searchWay)));
        cls.get(9).setOnEditCommit(
                (TableColumn.CellEditEvent<TableColumnInfoModel, Object> t) -> {
                    TableColumnInfoModel rowData = t.getTableView().getItems().get(t.getTablePosition().getRow());
                    rowData.setSearchWay(t.getNewValue().toString());
                    tableView.refresh();
                }
        );

        List<String> otherOpt = new ArrayList<>();
        otherOpt.add("and");
        otherOpt.add("or");
        cls.get(10).setCellValueFactory(new PropertyValueFactory<>("otherOpt"));
        cls.get(10).setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(otherOpt)));
        cls.get(10).setOnEditCommit(
                (TableColumn.CellEditEvent<TableColumnInfoModel, Object> t) -> {
                    TableColumnInfoModel rowData = t.getTableView().getItems().get(t.getTablePosition().getRow());
                    rowData.setOtherOpt(t.getNewValue().toString());
                    tableView.refresh();
                }
        );

        cls.get(11).setCellValueFactory(new PropertyValueFactory("frontShow"));
        cls.get(11).setCellFactory(CellFactory.tableCheckBoxColumn(index -> {
            TableColumnInfoModel model = (TableColumnInfoModel) tbColumnInfos.getItems().get(index);
            ObservableValue<Boolean> ret =
                    new SimpleBooleanProperty(model, "frontShow", model.isFrontShow());
            ret.addListener((observable, oldValue, newValue) -> model.setFrontShow(newValue));
            return ret;
        }));
        tbColumnInfos.getItems().clear();
        ObservableList<TableColumnInfoModel> fliest = FXCollections.observableList(tableDataList);
        tbColumnInfos.getItems().addAll(fliest);
    }

}
