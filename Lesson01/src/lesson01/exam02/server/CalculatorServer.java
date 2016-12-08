package lesson01.exam02.server;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
//이건 1:1의 대화이며 다수의 대화를 만들고 싶으면 다수의 thread를 만들면 된다.
public class CalculatorServer {
	private int port;

	//생성자 : port번호를 매번 바꿀 수 있다. port를 막아 방어벽
	public CalculatorServer(int port) {
		this.port = port;
	}
	
	@SuppressWarnings("resource")
  public void service() throws Exception {
		ServerSocket serverSocket = new ServerSocket(port);	//받은내용 
												//ㄴ 받는 용도의 상자
		System.out.println("CalculatorServer startup:");	//어떤 내용을 보낼지
		
		Socket socket = null;
				// ㄴ 보내는 용도의 상자
		
		while(true) {	//서버 꺼지면 안되니까 무한루프
			try {
				System.out.println("waiting client...");
				
				socket = serverSocket.accept();		//서버 소켓을 받는다.
				System.out.println("connected to client.");
				
				processRequest(socket);
				System.out.println("closed client.");
				
			} catch (Throwable e) {
				System.out.println("connection error!");
			}
		}
	}
	//소켓이 담아 보내면 읽고 왔다갓다 하는 것이다.
	private void processRequest(Socket socket) throws Exception {
		Scanner in = new Scanner(socket.getInputStream());
		//ㄴ inputstream의 한 형태
		PrintStream out = new PrintStream(socket.getOutputStream());
				
		String operator = null;
		double a, b, r;
		
		while(true) {
			try {
				operator = in.nextLine();
				//ㄴ 텍스트 상자 안에 출력해라. 첫번째 줄
				if (operator.equals("goodbye")) {
					out.println("goodbye");
					break;
					
				} else {
					a = Double.parseDouble(in.nextLine());	//두번째 줄
					b = Double.parseDouble(in.nextLine());	//세번재 줄
					r = 0;
				
					switch (operator) {
					case "+": r = a + b; break;
					case "-": r = a - b; break;
					case "*": r = a * b; break;
					case "/": 
						if (b == 0) throw new Exception("0 으로 나눌 수 없습니다!");
						r = a / b; 
						break;
					default:
						throw new Exception("해당 연산을 지원하지 않습니다!");
					}// 계산해서 outstream
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
	//1) 8888포트를 연다
	public static void main(String[] args) throws Exception {
		CalculatorServer app = new CalculatorServer(8888);
		app.service();
	}
}