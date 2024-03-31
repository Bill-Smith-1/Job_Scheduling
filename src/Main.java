import java.util.*;
class Data{
    String[] job_name=new String[6];
    int[] arrival_time=new int[6];
    int[] service_time=new int[6];
}
class JobData{
    String job_name;
    int arrival_time;
    int service_time;
    public JobData(String name, int arrivalTime, int serviceTime) {
        this.job_name = name;
        this.arrival_time = arrivalTime;
        this.service_time = serviceTime;
    }
}
class OverData{
    String[] job_name=new String[6];
    int[] bigin=new int[6];
    int[] over=new int[6];
    int[] dual=new int[6];
    double[] turnaround_time_weight=new double[6];
}
class Over{
    String job_name;
    int bigin;
    int over;
    int dual;
    double turnaround_time_weight;
}
public class Main {
    static Data data = new Data();
    static JobData[] jobData=new JobData[6];

    public static void input() {
        jobData= new JobData[]{
                new JobData("A", 0, 6),
                new JobData("B", 2, 50),
                new JobData("C", 5, 20),
                new JobData("D", 5, 10),
                new JobData("E", 12, 40),
                new JobData("F", 15, 8)
        };
    }
    public static void FCFS(){
        System.out.println("FCFS算法：");
        implant();
        System.out.println("作业顺序为：");
        for (int i = 0; i < 6; i++) {
            System.out.print(jobData[i].job_name+" ");
        }
    }
    public static void implant(){
        Over[] over= new Over[6];
        int sum1=0;
        double sum2=0;
        for(int i=0;i<6;i++) {
            over[i] = new Over();
            over[i].job_name=jobData[i].job_name;
            if(i==0) over[i].bigin=0;
            else over[i].bigin=over[i-1].over;
            over[i].over=over[i].bigin+jobData[i].service_time;
            over[i].dual=over[i].over-jobData[i].arrival_time;
            over[i].turnaround_time_weight=over[i].dual/jobData[i].service_time;
        }
        for(int i=0;i<6;i++){
            sum1+=over[i].dual;
        }
        for(int i=0;i<6;i++){
            sum2+=over[i].turnaround_time_weight;
        }
        System.out.println("周转时间平均值为:" +sum1/6);
        System.out.println("带权周转时间平均值为:" +sum2/6);
    }
    public static void input_Data() {

        for(int i=0;i<6;i++) {

            Scanner scanner = new Scanner(System.in);
            System.out.print("请输入作业名：");
            String assignmentName = scanner.nextLine();
            System.out.print("请输入到达时间：");
            int arrivalTime = scanner.nextInt();
            System.out.print("请输入服务时间：");
            int serviceTime = scanner.nextInt();
            jobData[i].job_name = assignmentName;
            jobData[i].arrival_time = arrivalTime;
            jobData[i].service_time = serviceTime;
        }
    }
    public static void swapObjects(Object[] objects, int index1, int index2) {
        if (index1 != index2 && index1 >= 0 && index2 >= 0 && index1 < objects.length && index2 < objects.length) {
            Object temp = objects[index1]; // 临时保存第一个对象
            objects[index1] = objects[index2]; // 将第一个对象指向第二个对象
            objects[index2] = temp; // 将第二个对象指向临时对象
        }
    }
    public static void SJF_change(){
        int sum=jobData[0].service_time;
        int index=0;
        int min=10000;
        for(int u=1;u<6;u++) {
            min=10000;
            for (int i = u; i < 6; i++) {
                if (jobData[i].arrival_time <= sum && jobData[i].service_time < min) {
                    min = jobData[i].service_time;
                    index = i;
                }
            }
            sum+=jobData[index].service_time;
            swapObjects(jobData,u,index);
        }
    }
    public static void HRRN_change(){
        int sum=jobData[0].service_time;
        int index=0;
        double max=0;
        double weight;
        for(int u=1;u<6;u++) {
            max=0;
            for (int i = u; i < 6; i++) {
                int waitingTime = sum - jobData[i].arrival_time;
                weight = (waitingTime + jobData[i].service_time) / (double) jobData[i].service_time;

                if (jobData[i].arrival_time <= sum && weight > max) {
                    max = weight;
                    index = i;
                }
            }
            sum+=jobData[index].service_time;
            swapObjects(jobData,u,index);

        }
    }
    public static void printd(){
        for (int i = 0; i < 6; i++) {
            System.out.print(jobData[i].job_name+" ");
        }
        System.out.println();
    }
    public static void SJF(){
        System.out.println("SJF算法：");
        SJF_change();
        implant();
        System.out.println("作业顺序为：");
        for (int i = 0; i < 6; i++) {
            System.out.print(jobData[i].job_name+" ");
        }
    }
    public static void HRRN(){
        System.out.println("HRRN算法：");
        HRRN_change();
        implant();
        System.out.println("作业顺序为：");
        for (int i = 0; i < 6; i++) {
            System.out.print(jobData[i].job_name+" ");
        }
    }
    public static void main(String args[]){
        input();
        FCFS();
    }
}
