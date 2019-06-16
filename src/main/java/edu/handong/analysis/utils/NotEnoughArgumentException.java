
package edu.handong.analysis.utils;
/**
 * With Customized Exception, you only need to extend the Exception and implement two constructors as you learned in the class. Refer to Exception message below.
 * 사용자 지정 예외를 사용하면 예외를 확장하고 수업에서 배운 대로 두 개의 생성자만 구현하면 된다. 
 * 아래의 예외 메시지를 참조하십시오.
 * @author eyuuo
 */
public class NotEnoughArgumentException extends Exception{

	//더 추가해야 하는 내용 있는지 확인하기.
	public NotEnoughArgumentException() {
		super("Not Enough Argument !!");
	}
	public NotEnoughArgumentException(String message) {
		super(message);
	}

}


