package com.liuzj.zipkinserver.designMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 命令模式: 当有新的命令添加的时候只需要新增一个新的命令并且在执行者中再添加一个命令就行
 */
public class CommandTest {
    public static void main(String[] args) {
        ReceiveCommand receiveCommand = new ReceiveCommand();

        GoCommand goCommand = new GoCommand(receiveCommand);
        ComeCommand comeCommand = new ComeCommand(receiveCommand);

        PutCommand putCommand = new PutCommand();
        putCommand.setCommand(goCommand);
        putCommand.setCommand(comeCommand);
        putCommand.commandExcute();
    }
}

/**
 * 命令
 */
abstract class Command{
    public ReceiveCommand receiveCommand;

    public Command(ReceiveCommand receiveCommand){
        this.receiveCommand = receiveCommand;
    }

    abstract void putCommand();
}

/**
 * 接收命令的人
 */
class ReceiveCommand{

    public void go(){
        System.out.println("Go");
    }

    public void come(){
        System.out.println("Come");
    }
}

/**
 * go命令实现类
 */
class GoCommand extends Command{

    public GoCommand(ReceiveCommand receiveCommand) {
        super(receiveCommand);
    }

    @Override
    void putCommand() {
        super.receiveCommand.go();
    }
}

/**
 * come命令实现类
 */
class ComeCommand extends Command{

    public ComeCommand(ReceiveCommand receiveCommand) {
        super(receiveCommand);
    }

    @Override
    void putCommand() {
        super.receiveCommand.come();
    }
}

/**
 * 发布命令的人
 */
class PutCommand{
    List<Command> commandList = new ArrayList<>();

    public void setCommand(Command command){
        commandList.add(command);
    }

    public void commandExcute(){
        commandList.forEach(command -> command.putCommand());
    }
}
