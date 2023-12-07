package com.example.intuitcraftround.service.impl;

import com.example.intuitcraftround.model.Node;
import com.example.intuitcraftround.service.TreeConstructorService;
import com.example.intuitcraftround.validation.TreeConstructionValidator;
import com.example.intuitcraftround.validation.impl.DuplicateValidator;
import com.example.intuitcraftround.validation.impl.FormatValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.example.intuitcraftround.constants.TreeConstructorConstants.DELIMITER;
import static com.example.intuitcraftround.constants.TreeConstructorConstants.NULL_IDENTIFIER;

@Service
@Slf4j
public class TreeConstructorServiceImpl implements TreeConstructorService {


    /**
     *
     * Constructs Tree from preOrder traversal String, considering # as null
     */
    private Node deSerialize(List<String> l) {
        if (l.get(0).equals(NULL_IDENTIFIER)) {
            l.remove(0);
            return null;
        }
        Integer id = Integer.valueOf(l.get(0));
        Node root = Node.builder().id(id).build();
        l.remove(0);

        root.setLeft(deSerialize(l));
        root.setRight(deSerialize(l));
        return root;
    }

    private void validateInput(String[] dataSplitString) {
        List<TreeConstructionValidator> validators = Arrays.asList(
                new FormatValidator(),
                new DuplicateValidator()
        );

        for (TreeConstructionValidator validator : validators) {
            validator.validate(dataSplitString);
        }
    }


    public Node constructTree(String data) {
        log.info("Constructing Tree for data: {}", data);

        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Input data is null or empty");
        }

        String[] dataSplitString = data.split(DELIMITER);
        validateInput(dataSplitString);

        List<String> dataList = new LinkedList<>(Arrays.asList(dataSplitString));
        return deSerialize(dataList);
    }
}
