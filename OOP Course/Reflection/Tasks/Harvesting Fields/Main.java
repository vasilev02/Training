import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {


		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		Field[] fields = RichSoilLand.class.getDeclaredFields();

		String command = buff.readLine();

		while (!command.equals("HARVEST")){

			if(command.equals("all")){

				for (Field current : fields) {
					sb.append(String.format("%s %s %s", Modifier.toString(current.getModifiers()),current.getType().getSimpleName(),current.getName()));
					sb.append(System.lineSeparator());
				}

			}else{

				String finalCommand = command;
				Arrays.stream(fields).filter(f -> Modifier.toString(f.getModifiers()).equals(finalCommand))
						.forEach(f -> {
							sb.append(String.format("%s %s %s",Modifier.toString(f.getModifiers()),f.getType().getSimpleName(),f.getName()));
							sb.append(System.lineSeparator());
						});

			}


			command = buff.readLine();
		}

		System.out.println(sb.toString().trim());

	}
}
