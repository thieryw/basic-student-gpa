import java.util.Arrays;


public class StudentUtil {

    public static double[] calculateGPA(int[] studentIdList, char[][] studentsGrades) {
        double[] gpaArray = new double[studentIdList.length];
        double gpa = 0;
        for(int i = 0, listLength = studentIdList.length; i < listLength; i++){
            for (char cs : studentsGrades[i]) {
                switch(cs){
                    case 'A' : gpa += 4; break;
                    case 'B' : gpa += 3; break;
                    case 'C' : gpa += 2; break;
                }
            }
            gpa /= studentsGrades[i].length;
            gpaArray[i] = gpa;
            gpa = 0;
        }

        return gpaArray;
    }

    private static boolean isValidParameters(double lower, double higher){

        return !(lower > higher || lower < 2 || higher > 4);

    }

    private static int getNumberOfStudentsWithinRange(double lower, double higher, double[] gpa){
        int numberOfStudents = 0;

        for (double d : gpa) {
            if(d >= lower && d <= higher){
                numberOfStudents++;
            }
        }

        return numberOfStudents;
    }



    
    public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentsGrades) {

        

        if (!isValidParameters(lower, higher)){
            return null;
        }

        int[] students = new int[getNumberOfStudentsWithinRange(lower, higher, calculateGPA(studentIdList, studentsGrades))];
        double[] gpa = calculateGPA(studentIdList, studentsGrades);
        int studentIdListIndex = 0;
        int studentIndex = 0;


        for (double d : gpa) {

            if(d >= lower && d <= higher){
                students[studentIndex] = studentIdList[studentIdListIndex];
                studentIndex++;
            }

            studentIdListIndex++;
            
        }

            

        return students;
        
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(calculateGPA(new int[]{1,2,3,4}, new char[][]{{'A','A','C','B'},{'C','A','C','B'},{'A','B','C','B'}, {'C','B','C','B'}})) + '\n' +
        Arrays.toString(getStudentsByGPA(2.5, 4, new int[]{1,2,3,4}, new char[][]{{'A','A','C','B'},{'C','A','C','B'},{'A','B','C','B'}, {'C','B','C','B'}})));

    }
    
}