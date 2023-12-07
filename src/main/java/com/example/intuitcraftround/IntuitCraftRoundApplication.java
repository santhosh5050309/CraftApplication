package com.example.intuitcraftround;

import com.example.intuitcraftround.controller.TreeController;
import com.example.intuitcraftround.model.Node;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class IntuitCraftRoundApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntuitCraftRoundApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(TreeController controller) {
        return args -> {
            entrypoint(controller);
        };
    }

    void entrypoint(TreeController controller) {
        while(true) {
            try {
                Node root;
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter the data for tree construction");
                String data = sc.next();
                root = controller.constructTree(data);
                System.out.println("Enter Node to be removed");
                int id = sc.nextInt();

                List<Integer> idList = controller.removeNode(root, id, "nodeRemovalByLevelOrderStrategy");

                System.out.println("Subtree root ids: ");
                for (Integer nodeId : idList) {
                    System.out.print(nodeId + " ");
                }
                System.out.println();
            } catch (Exception ex) {
                System.out.println("Invalid Input, try again " + ex.getMessage());
            }
        }
    }
}
