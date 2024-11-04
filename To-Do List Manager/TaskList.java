class TaskNode {
    Task task;
    TaskNode next;

    public TaskNode(Task task) {
        this.task = task;
        this.next = null;
    }
}

public class TaskList {
    private TaskNode head;

    public void addTask(String description) {
        Task newTask = new Task(description);
        TaskNode newNode = new TaskNode(newTask);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void markTaskAsCompleted(int index) {
        TaskNode current = head;
        for (int i = 0; i < index; i++) {
            if (current == null) {
                System.out.println("Task not found.");
                return;
            }
            current = current.next;
        }
        if (current != null) {
            current.task.markAsCompleted();
        } else {
            System.out.println("Task not found.");
        }
    }

    public void printTasks() {
        TaskNode current = head;
        int count = 1;
        while (current != null) {
            System.out.println(count + ". " + current.task);
            current = current.next;
            count++;
        }
    }
}
