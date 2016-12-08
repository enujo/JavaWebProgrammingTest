package lesson01.exam01;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
//AWT -> SWING API
@SuppressWarnings("serial")//����� �Ⱥ��̰�		// �������̽� ����
public class CalculatorFrame extends JFrame implements ActionListener{		//������ JFrame �ε� Actionlistener�� �Ѵ�
								//�����	�����Ű�� â�� ����ִ� Ŭ����
		
	JTextField operand1 = new JTextField(4);	//���ڸ� �Է� �Ҽ� �ִ� ����
	JTextField operand2 = new JTextField(4);
	String[] operatorData = {"+", "-", "*", "/"};
	JComboBox<String> operator = new JComboBox<String>(operatorData);	//�����ϴ� �ڽ� (select)
	JButton equal = new JButton("=");	//��ư�� �־��
	JTextField result = new JTextField(6);
	JButton clear = new JButton("Clear");
	
	public static void main(String[] args) {
		CalculatorFrame app = new CalculatorFrame();
		app.setVisible(true);	//���� ���̰�
	}//main �� ���� �Ǿ��ִµ� �ٸ� thread�� ����ִ� �� (GUI thread ) JFrame�� ��ӹ����� �ڵ����� thread ����
	
	//�� ��ü�� ��������� ����ȴ�. �׸𳭰� ���������
	public CalculatorFrame() {
		this.setTitle("Lesson01-Exam01");	//title�� ���������.
		
		Container contentPane = this.getContentPane();	//�����Ӿȿ� ä�� �� ���� ����� div
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
										//�� ��Ƽ�� ������ §��
		contentPane.add(Box.createVerticalGlue());	//Box.���̾ƿ� ����
		contentPane.add(this.createInputForm());	//���� ���� Box
		contentPane.add(this.createToolBar());
		contentPane.add(Box.createVerticalGlue());
											//JFrame �� ������ �ִ� Ŭ���� ���� �̷� ���ڰ��� ��������� ���Ḧ �ϰٴ�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					//�� ����  - �� X ���   X �� ����� ���� ȭ���� �����.
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
			//Ŭ���ϸ�
		if (event.getSource() == equal) {
			compute();	//����
		} else {
			clearForm();
		}
	}
	
	private void compute() {
		double a = Double.parseDouble(operand1.getText()); 
		double b = Double.parseDouble(operand2.getText());
		double r = 0;
		
		try {		
			switch (operator.getSelectedItem().toString()) {
			case "+": r = a + b; break;
			case "-": r = a - b; break;
			case "*": r = a * b; break;
			case "/": 
				if (b == 0) throw new Exception("0 ���� ���� �� �����ϴ�!");
				r = a / b; break;
			}
			
			result.setText(Double.toString(r));
			
		} catch (Exception err) {
			JOptionPane.showMessageDialog(
				null, err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void clearForm() {
		this.operand1.setText("");
		this.operand2.setText("");
		this.result.setText("");
	}
	
	// Box�� ����� ��
	private Box createInputForm() {
		Box box = Box.createHorizontalBox();
		box.setMaximumSize(new Dimension(300, 30));
		box.setAlignmentY(Box.CENTER_ALIGNMENT);
		box.add(operand1);
		box.add(operator);
		box.add(operand2);
		box.add(equal);	// = �̰�
		box.add(result);
		equal.addActionListener(this);		//javascript ���� click
		return box;			// �� Ŭ�������� ����Ǵ� �Լ��� ���;� �Ѵ� �׷����� �Լ��� ���� ��ü�� ���� �Ѵ�. �ڱ��ڽ�
	}							// �� �͸� Ŭ������ ���� �ڹٿ�����
	// �ι�° �ڽ�
	private Box createToolBar() {
		Box box = Box.createHorizontalBox();
		box.add(clear);
		clear.addActionListener(this);
		return box;
	}
	
	
}