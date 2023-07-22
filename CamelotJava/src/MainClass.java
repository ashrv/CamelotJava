public class MainClass {
	public static void main(String[] args) {
		try {
		System.out.println("Hello");
		var story = new SampleStory();
		System.out.println("Bye");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}
}
