import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.*;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {


		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();



		Class<BlackBoxInt> box = BlackBoxInt.class;

		Constructor<BlackBoxInt> ctor = box.getDeclaredConstructor();

		ctor.setAccessible(true);

		BlackBoxInt blackBoxInt = ctor.newInstance();

		Method[] methods = box.getDeclaredMethods();

		Field field = box.getDeclaredField("innerValue");

		field.setAccessible(true);


		String[] command = buff.readLine().split("\\_");

		while (!command[0].equals("END")){

			String operand = command[0];

			int value = Integer.parseInt(command[1]);

			for (Method method : methods) {

				if(method.getName().equals(operand)){
					method.setAccessible(true);
					method.invoke(blackBoxInt,value);
					System.out.println(field.getInt(blackBoxInt));
					break;
				}

			}

			command = buff.readLine().split("\\_");
		}

	}
}
