package com.epam.mjc;

import java.util.ArrayList;

enum SignatureToken {
    ACCESS_MODIFIER, RETURN_TYPE, METHOD_NAME,
}

enum ArgumentToken {
    TYPE, NAME,
}

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        int argsStartIndex = signatureString.indexOf('(');

        String argsWithTypes = signatureString.substring(argsStartIndex + 1, signatureString.length() - 1);
        String signature = signatureString.substring(0, argsStartIndex);

        String[] signatureTokens = signature.split(" ");
        int accessModifierOffset = signatureTokens.length >= 3 ? 0 : 1;
        boolean hasAccessModifier = signatureTokens.length >= 3;

        MethodSignature methodSignature = new MethodSignature(signatureTokens[SignatureToken.METHOD_NAME.ordinal() - accessModifierOffset],
                getArguments(argsWithTypes));
        methodSignature.setReturnType(signatureTokens[SignatureToken.RETURN_TYPE.ordinal() - accessModifierOffset]);

        if (hasAccessModifier) {
            methodSignature.setAccessModifier(signatureTokens[SignatureToken.ACCESS_MODIFIER.ordinal()]);
        }

        return methodSignature;
    }

    private static ArrayList<MethodSignature.Argument> getArguments(String args) {
        ArrayList<MethodSignature.Argument> argsList = new ArrayList<>();
        if (args.length() >= 1) {
            for (String arg : args.split(",")) {
                String[] argTypeWithName = arg.trim().split(" ");
                MethodSignature.Argument argument = new MethodSignature.Argument(argTypeWithName[ArgumentToken.TYPE.ordinal()],
                        argTypeWithName[ArgumentToken.NAME.ordinal()]);
                argsList.add(argument);
            }

        }

        return argsList;
    }
}
