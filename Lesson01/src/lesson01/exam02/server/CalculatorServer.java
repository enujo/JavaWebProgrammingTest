package lesson01.exam02.server;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
//�̰� 1:1�� ��ȭ�̸� �ټ��� ��ȭ�� ����� ������ �ټ��� thread�� ����� �ȴ�.
public class CalculatorServer {
	private int port;

	//������ : port��ȣ�� �Ź� �ٲ� �� �ִ�. port�� ���� ��
	public CalculatorServer(int port) {
		this.port = port;
	}
	
	@SuppressWarnings("resource")
  public void service() throws Exception {
		ServerSocket serverSocket = new ServerSocket(port);	//�������� 
												//�� �޴� �뵵�� ����
		System.out.println("CalculatorServer startup:");	//� ������ ������
		
		Socket socket = null;
				// �� ������ �뵵�� ����
		
		while(true) {	//���� ������ �ȵǴϱ� ���ѷ���
			try {
				System.out.println("waiting client...");
				
				socket = serverSocket.accept();		//���� ������ �޴´�.
				System.out.println("connected to client.");
				
				processRequest(socket);
				System.out.println("closed client.");
				
			} catch (Throwable e) {
				System.out.println("connection error!");
			}
		}
	}
	//������ ��� ������ �а� �Դٰ��� �ϴ� ���̴�.
	private void processRequest(Socket socket) throws Exception {
		Scanner in = new Scanner(socket.getInputStream());
		//�� inputstream�� �� ����
		PrintStream out = new PrintStream(socket.getOutputStream());
				
		String operator = null;
		double a, b, r;
		
		while(true) {
			try {
				operator = in.nextLine();
				//�� �ؽ�Ʈ ���� �ȿ� ����ض�. ù��° ��
				if (operator.equals("goodbye")) {
					out.println("goodbye");
					break;
					
				} else {
					a = Double.parseDouble(in.nextLine());	//�ι�° ��
					b = Double.parseDouble(in.nextLine());	//������ ��
					r = 0;
				
					switch (operator) {
					case "+": r = a + b; break;
					case "-": r = a - b; break;
					case "*": r = a * b; break;
					case "/": 
						if (b == 0) throw new Exception("0 ���� ���� �� �����ϴ�!");
						r = a / b; 
						break;
					default:
						throw new Exception("�ش� ������ �������� �ʽ��ϴ�!");
					}// ����ؼ� outstream
					out.println("success");
					out.println(r);
				}
				
			} catch (Exception err) {
				out.println("failure");
				out.println(err.getMessage());
			}
		}
		
		try {out.close();} catch (Exception e) {}
		try {in.close();} catch (Exception e) {}
		try {socket.close();} catch (Exception e) {}
	}
	//1) 8888��Ʈ�� ����
	public static void main(String[] args) throws Exception {
		CalculatorServer app = new CalculatorServer(8888);
		app.service();
	}
}