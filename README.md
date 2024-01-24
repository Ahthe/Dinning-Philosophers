# Dining Philosophers Simulator 🍽️🤔

## 1. Introduction

### 1.1 Problem Description
The Dining Philosophers issue is a classic example in concurrent programming that explores resource sharing and synchronization challenges. Philosophers alternate between thinking 🤔 and eating 🍴 in a dining setup; however, they must share common resources (forks). This problem highlights the potential for deadlock and underscores the necessity for synchronization mechanisms to avoid such scenarios.

### 1.2 Objective
The main goal of this simulator is to create an interactive and visually appealing tool for learning the Dining Philosophers problem and implementing deadlock prevention solutions. The key objectives include graphically representing philosopher states and behaviors, integrating Java synchronization primitives, and providing real-time updates in a user-friendly JavaFX environment.

## 2. Synchronization Technique

### 2.1 Deadlock Prevention
The chosen synchronization mechanism combines explicit locks and synchronized methods. Philosophers follow a protocol for acquiring forks that prevents cyclic waiting, a crucial condition for deadlock. The synchronization approach strongly focuses on orderly resource acquisition and release to prevent deadlock scenarios.

### 2.2 Rationale
The applied synchronization strategy is justified by its effectiveness in addressing the challenges presented by the Dining Philosophers problem. By carefully timing the philosophers' fork acquisitions and releases, the strategy reduces the chance of cyclic waiting and, therefore, the risk of deadlock.

## 3. GUI Representation for JavaFX

### 3.1 Design Layout
The GUI represents philosophers and the dining table as visually distinct entities. Forks are thoughtfully positioned between seated philosophers, depicted as animated figures. The graphical layout’s design portrays the simulated scenario intuitively and clearly.

### 3.2 States of Philosophers
Each philosopher can be in one of three possible states, each indicated by a different set of visual cues. A philosopher displays a thought bubble while thinking 💭; a fork and knife animation signifies waiting 🍴, and so on. These philosophical states are continually updated in real-time by the GUI.

## 4. Control Flow Simulation

### 4.1 Buttons for JavaFX
JavaFX buttons are integrated into the simulator to control the simulation flow. Buttons like "Start" 🟢, "Pause" ⏸️, and "Reset" 🔁 allow users to dynamically adjust the flow, and they are synchronized with the underlying simulation logic for smooth and consistent behavior.

### 4.2 Integration of Synchronization
The graphical interface seamlessly incorporates Java synchronization primitives. Critical sections of the code, like philosophers picking up or putting down forks, are properly synchronized. This integration ensures thread safety and proper functioning of the simulation by avoiding race conditions.

## 5. Real-time Updates

### 5.1 Dynamic Updates
The GUI’s real-time updates allow users to observe and understand the dynamic nature of philosopher interactions. Smooth transitions between eating, waiting, and thinking are presented, offering an immersive experience that enhances learning.

### 5.2 Responsiveness
The GUI is responsively designed to optimize user experience. Rapid processing of updates when philosophers change states ensures that the simulation runs smoothly and responsively, even in complex synchronization settings.

## 6. Visual Documentation

### 6.1 Images
Key scenes from the simulation are captured in a series of images that provide a visual walkthrough of mental states and their transitions. These images offer a comprehensive visual record of the simulator's functionality.

1. Start the program by running `DinningPhilosophers.java`.
2. The output you will get is represented below in the image.

![image](https://github.com/Ahthe/Dinning-Philosophers/assets/107819350/91dcb4da-3b47-4bc7-a495-d74a6c691291)

![image](https://github.com/Ahthe/Dinning-Philosophers/assets/107819350/ab0561e9-23e0-4994-9e3f-9d1c0621d338)


## 7. Educational Value

### 7.1 Learning Outcomes
The simulator serves as a practical tool for learning about concurrent programming and deadlock prevention, making it an invaluable educational resource. Learners gain a better understanding of concurrent programming topics as they interact hands-on with synchronization mechanisms and resource-sharing challenges.

### 7.2 Future Extensions
Future extensions could include additional features or problems related to concurrent programming to further enhance the educational value. Alternatives include varying the synchronization techniques or adding more complex resource-sharing scenarios, offering students a range of challenges to tackle.

## 8. Conclusion
In conclusion, the Dining Philosophers problem simulator provides a comprehensive and engaging tool for teaching deadlock avoidance in concurrent programming. By employing effective synchronization strategies and a well-designed JavaFX GUI, the simulator offers a practical learning environment that elevates the comprehension and engagement of complex concepts for both educators and students.
