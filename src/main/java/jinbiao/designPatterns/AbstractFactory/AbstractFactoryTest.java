package jinbiao.designPatterns.AbstractFactory;

public class AbstractFactoryTest {
    public static void main(String[] args) {
        IDatabaseUtils mysqlUtils=new MysqlIDatabaseUtils();  //父类的引用指向子类对象
        IConnection mysqlConnection = mysqlUtils.getConnection();
        mysqlConnection.connect();
        ICommand mysqlCommand = mysqlUtils.getCommand();
        mysqlCommand.command();

        IDatabaseUtils oracleUtils=new OracleIDatabaseUtils();  //父类的引用指向子类对象
        IConnection oracleConnection = oracleUtils.getConnection();
        oracleConnection.connect();
        ICommand oracleCommand = oracleUtils.getCommand();
        oracleCommand.command();
    }
}

//针对数据库变化的部分:  mysql   oracle
//变化的部分的共性:   connection,command
interface IConnection{
  void connect();
}

interface ICommand{
    void command();
}

interface IDatabaseUtils{
    IConnection getConnection();
    ICommand getCommand();
}

class MysqlIConnection implements IConnection{
    @Override
    public void connect() {
        System.out.println("mysql connected...");
    }
}

class OracleIConnection implements IConnection{
    @Override
    public void connect() {
        System.out.println("oracle connected...");
    }
}

class MysqlICommand implements ICommand{
    @Override
    public void command() {
        System.out.println("mysql command...");
    }
}

class OracleICommand implements ICommand{
    @Override
    public void command() {
        System.out.println("oracle command...");
    }
}

class MysqlIDatabaseUtils implements IDatabaseUtils{
    @Override
    public IConnection getConnection() {
        return new MysqlIConnection();
    }
    @Override
    public ICommand getCommand() {
        return new MysqlICommand();
    }
}

class OracleIDatabaseUtils implements IDatabaseUtils{
    @Override
    public IConnection getConnection() {
        return new OracleIConnection();
    }
    @Override
    public ICommand getCommand() {
        return new OracleICommand();
    }
}