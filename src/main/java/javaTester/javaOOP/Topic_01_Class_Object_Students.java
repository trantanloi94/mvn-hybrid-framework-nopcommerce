package javaTester.javaOOP;

public class Topic_01_Class_Object_Students {
    private int studentID;
    private String studentName;
    private Float theoreticalPoint;
    private Float practicePoint;

    //Getter & Setter
    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Float getTheoreticalPoint() {
        return theoreticalPoint;
    }

    public void setTheoreticalPoint(Float theoreticalPoint) {
        this.theoreticalPoint = theoreticalPoint;
    }

    public Float getPracticePoint() {
        return practicePoint;
    }

    public void setPracticePoint(Float practicePoint) {
        this.practicePoint = practicePoint;
    }

    private Float getAveragePoint() {
        return (theoreticalPoint + practicePoint * 2) / 3;
    }

    private void showStudentInfo() {
        System.out.println("*********************************");
        System.out.println("Student ID: " + getStudentID());
        System.out.println("Student name: " + getStudentName());
        System.out.println("Student Theoretical Point: " + getTheoreticalPoint());
        System.out.println("Student Practice Point: " + getPracticePoint());
        System.out.println("Student Average Point: " + getAveragePoint());
        System.out.println("*********************************");
    }

    public static void main(String[] args) {
        //First student
        Topic_01_Class_Object_Students firstStudent = new Topic_01_Class_Object_Students();
        firstStudent.setStudentID(123);
        firstStudent.setStudentName("LOI TRAN");
        firstStudent.setTheoreticalPoint(7.9f);
        firstStudent.setPracticePoint(9.8f);
        firstStudent.showStudentInfo();

        //Second student
        Topic_01_Class_Object_Students secondStudent = new Topic_01_Class_Object_Students();
        firstStudent.setStudentID(444);
        firstStudent.setStudentName("LOI NGUYEN");
        firstStudent.setTheoreticalPoint(3.8f);
        firstStudent.setPracticePoint(4.8f);
        firstStudent.showStudentInfo();
    }


}
