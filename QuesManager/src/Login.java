
import javax.swing.*;
import java.awt.*;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {

    //定义登录界面的组件
    JButton jb1,jb2,jb3=null;
    JRadioButton jrb1,jrb2,jrb3=null;
    JPanel jp1,jp2,jp3,jp4=null;
    JTextField jtf=null;
    JLabel jlb1,jlb2,jlb3=null;
    JPasswordField jpf=null;
    ButtonGroup bg=null;


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Login  ms=new Login();


    }
    //构造函数
    public Login()
    {
        this.setTitle("题库管理系统");
        final JPanel panel = new LoginPanel();
        //JPanel panel = new JPanel();

        //用户名
        jlb1=new JLabel("用户名：");
        jlb1.setBounds(450,400,1000,90);
        jlb1.setFont(new Font("微软雅黑", Font.BOLD, 30));
        panel.add(jlb1);//jp1.add(jlb1);
        //jtf=new JTextField(10);
        jtf = new JTextField();
        jtf.setBounds(600,415,500,60);
        jtf.setFont(new Font("微软雅黑", Font.BOLD, 30));
        panel.add(jtf);//jp1.add(jtf);
        //密码
        jlb2=new JLabel("密   码：");
        jlb2.setBounds(450,480,1000,90);
        jlb2.setFont(new Font("微软雅黑", Font.BOLD, 30));
        jpf=new JPasswordField(10);
        jpf.setBounds(600,495,500,60);
        jpf.setFont(new Font("微软雅黑", Font.BOLD, 30));
        panel.add(jlb2);//jp2.add(jlb2);
        panel.add(jpf);//jp2.add(jpf);

        //底下三个按钮
        JPanel jp4 = new JPanel();
        jp4.setBounds(300,600,1000,90);
        jp4.setBackground(Color.white);
        jp4.setLayout(new FlowLayout());
        jb1=new JButton("登录");
        jb1.addActionListener(this);
        jb1.setFont(new Font("微软雅黑", Font.BOLD, 30));
        jp4.add(jb1);
        jb2=new JButton("重置");
        jb2.addActionListener(this);
        jb2.setFont(new Font("微软雅黑", Font.BOLD, 30));
        jp4.add(jb2);
        jb3=new JButton("退出");
        jb3.addActionListener(this);
        jb3.setFont(new Font("微软雅黑", Font.BOLD, 30));
        jp4.add(jb3);

        panel.add(jp4);

        this.getContentPane().add(panel);
        this.setBounds(500,400,panel.getWidth(),panel.getHeight());
        panel.setLayout(null);

        //设置当关闭窗口时，保证JVM也退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //显示窗体
        this.setVisible(true);
        this.setResizable(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand()=="退出")
        {
            System.exit(0);
        }else if(e.getActionCommand()=="登录")
        {
            if(!jtf.getText().isEmpty() && !jpf.getText().isEmpty())
            {
                Dao con=new Dao("root","123456");
                con.queryUser(jtf.getText());
                //首先判断是否存在该用户，即是否得到了密码
                if(con.role ==null)
                {
                    this.clear();
                }else
                {
                    //调用登录方法
                    this.login(con.role,con.pwd);
                }
            }else if(jtf.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null,"请输入用户名","提示消息",JOptionPane.WARNING_MESSAGE);
                this.clear();
            }else if(jpf.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null,"请输入密码","提示消息",JOptionPane.WARNING_MESSAGE);
                this.clear();
            }
        }else if(e.getActionCommand()=="重置")
        {
            this.clear();
        }

    }
    //清空文本框和密码框
    public	void clear()
    {
        jtf.setText("");
        jpf.setText("");
    }

    //超级管理员登录判断方法
    public void login(String type,String pwd)
    {
        if(pwd.equals(jpf.getText()))
        {
//					System.out.println("登录成功");
            JOptionPane.showMessageDialog(null,"登录成功！","提示消息",JOptionPane.WARNING_MESSAGE);
            this.clear();
            //关闭当前界面
            dispose();
            switch (type){
                case "guanliyuan":{
                    guanliyuanUI ui0 =new guanliyuanUI();
                    break;
                }
                case "tikuyuan":{
                    tikuyuanUI ui1 = new tikuyuanUI();
                    break;
                }
                case "zujuanyuan":{
                    zujuanyuanUI ui2 = new zujuanyuanUI();
                    break;
                }
            }
//            //创建一个新界面，适用于超级管理员
//            guanliyuanUI ui =new guanliyuanUI();
        }else if(jtf.getText().isEmpty()&&jpf.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"请输入用户名和密码！","提示消息",JOptionPane.WARNING_MESSAGE);
        }else if(jtf.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"请输入用户名！","提示消息",JOptionPane.WARNING_MESSAGE);
        }else if(jpf.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"请输入密码！","提示消息",JOptionPane.WARNING_MESSAGE);
        }else
        {
            JOptionPane.showMessageDialog(null,"用户名或者密码错误！\n请重新输入","提示消息",JOptionPane.ERROR_MESSAGE);
            //清空输入框
            this.clear();
        }
    }

}