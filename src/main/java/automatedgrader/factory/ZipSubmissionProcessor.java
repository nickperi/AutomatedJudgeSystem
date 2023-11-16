package automatedgrader.factory;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

// Concrete Product Class for processing zip submissions
public class ZipSubmissionProcessor implements SubmisssionProcessor{
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Nicholas\\Desktop\\COMP 3607\\program1\\Client.java";

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            // Parse the content using JDT AST
            ASTParser parser = ASTParser.newParser(AST.JLS17);
            parser.setSource(content.toCharArray());
            parser.setKind(ASTParser.K_COMPILATION_UNIT);

            Map<String, String> attributes = new HashMap<>();

            // Visitor to traverse the AST
            ASTVisitor visitor = new ASTVisitor() {
                @Override
                public boolean visit(FieldDeclaration node) {
                    // Extract attribute names and types
                    for (Object fragment : node.fragments()) {
                        if (fragment instanceof VariableDeclarationFragment) {
                            VariableDeclarationFragment variable = (VariableDeclarationFragment) fragment;
                            String attributeName = variable.getName().getIdentifier();
                            String attributeType = node.getType().toString();
                            attributes.put(attributeName, attributeType);
                        }
                    }
                    return super.visit(node);
                }
            };

            CompilationUnit cu = (CompilationUnit) parser.createAST(null);
            cu.accept(visitor);

            // Now, you can check your attributes map for specific criteria
            for (Map.Entry<String, String> entry : attributes.entrySet()) {
                String attributeName = entry.getKey();
                String attributeType = entry.getValue();

                // Check criteria for attribute names and data types
                if (isValidAttributeName(attributeName) && isValidAttributeType(attributeType)) {
                    System.out.println("Attribute: " + attributeName + ", Type: " + attributeType);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isValidAttributeName(String attributeName) {
        // Add your criteria for valid attribute names
        return attributeName.matches("[a-zA-Z_][a-zA-Z0-9_]*");
    }

    private static boolean isValidAttributeType(String attributeType) {
        // Add your criteria for valid attribute types
        // For example, check if it's one of the allowed types
        return attributeType.equals("int") || attributeType.equals("String");
    }
}
