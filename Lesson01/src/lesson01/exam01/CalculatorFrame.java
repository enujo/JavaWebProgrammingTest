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
@SuppressWarnings("serial")//노란줄 안보이게		// 인터페이스 구현
public class CalculatorFrame extends JFrame implements ActionListener{		//원래는 JFrame 인데 Actionlistener도 한다
								//ㄴ상속	실행시키면 창을 띄워주는 클래스
		
	JTextField operand1 = new JTextField(4);	//글자를 입력 할수 있는 공간
	JTextField operand2 = new JTextField(4);
	String[] operatorData = {"+", "-", "*", "/"};
	JComboBox<String> operator = new JComboBox<String>(operatorData);	//선택하는 박스 (select)
	JButton equal = new JButton("=");	//버튼이 있어요
	JTextField result = new JTextField(6);
	JButton clear = new JButton("Clear");
	
	public static void main(String[] args) {
		CalculatorFrame app = new CalculatorFrame();
		app.setVisible(true);	//눈에 보이게
	}//main 은 종료 되어있는데 다른 thread가 살아있는 것 (GUI thread ) JFrame을 상속받으면 자동으로 thread 실행
	
	//이 객체가 만들어지면 실행된다. 네모난게 만들어진다
	public CalculatorFrame() {
		this.setTitle("Lesson01-Exam01");	//title을 만들어진다.
		
		Container contentPane = this.getContentPane();	//프레임안에 채울 수 없어 만든다 div
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
										//ㄴ 파티션 같은걸 짠것
		contentPane.add(Box.createVerticalGlue());	//Box.레이아웃 관련
		contentPane.add(this.createInputForm());	//리턴 값이 Box
		contentPane.add(this.createToolBar());
		contentPane.add(Box.createVerticalGlue());
											//JFrame 이 가지고 있는 클래스 변수 이런 숫자값을 가지고오면 종료를 하겟다
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					//ㄴ 맨위  - ㅁ X 기능   X 의 기능은 원래 화면을 숨긴다.
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
			//클릭하면
		if (event.getSource() == equal) {
			compute();	//실행
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
				if (b == 0) throw new Exception("0 으로 나눌 수 없습니다!");
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
	
	// Box를 만드는 것
	private Box createInputForm() {
		Box box = Box.createHorizontalBox();
		box.setMaximumSize(new Dimension(300, 30));
		box.setAlignmentY(Box.CENTER_ALIGNMENT);
		box.add(operand1);
		box.add(operator);
		box.add(operand2);
		box.add(equal);	// = 이거
		box.add(result);
		equal.addActionListener(this);		//javascript 에서 click
		return box;			// ㄴ 클릭햇을때 실행되는 함수가 들어와야 한다 그럼으로 함수를 가진 객체가 들어가야 한다. 자기자신
	}							// ㄴ 익명 클래스가 들어간다 자바에서는
	// 두번째 박스
	private Box createToolBar() {
		Box box = Box.createHorizontalBox();
		box.add(clear);
		clear.addActionListener(this);
		return box;
	}
	
	
}