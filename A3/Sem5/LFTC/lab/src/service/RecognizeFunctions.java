package service;

import automat.finit.AF;
import automat.finit.AutomateFinite;
import constants.Constants;
import constants.RecognizedAtoms;
import model.OutputMatch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecognizeFunctions {

    public static Map<RecognizedAtoms, Function<String, OutputMatch>> MAP = new HashMap<>() {{
       put(RecognizedAtoms.DELIMITER, RecognizeFunctions::identifyDelimiters);
       put(RecognizedAtoms.KEYWORD, RecognizeFunctions::identifyKeyWords);
       put(RecognizedAtoms.IDENTIFIER, RecognizeFunctions::identifyID);
       put(RecognizedAtoms.LITERAL, RecognizeFunctions::identifyLiteral);
       put(RecognizedAtoms.ASSIGNMENT_OPERATOR, RecognizeFunctions::identifyAssigmentOperator);
       put(RecognizedAtoms.ARITHMETIC_OPERATOR, RecognizeFunctions::identifyArithmeticOperator);
       put(RecognizedAtoms.RELATION_OPERATOR, RecognizeFunctions::identifyRelationOperator);
    }};

    private static String prepareText(String text){
        return text;
    }

    private static OutputMatch applyPattern(String text, String regex) {
        Pattern pattern = Pattern.compile(regex); // name of header with <>
        Matcher matcher = pattern.matcher(text);
        if(matcher.find())
            return new OutputMatch(matcher.start(), matcher.end(), matcher.group());
        return OutputMatch.Empty;
    }

    public static OutputMatch applyAF(String text, AF af){
        for(int i = 0; i < text.length(); i++){
            String extracted = text.substring(i);
            String result = af.findTheLongestPrefix(extracted);
            if(result.length() > 0)
                return new OutputMatch(i, i+result.length(), result);
        }
        return OutputMatch.Empty;
    }

    private static OutputMatch identifyDelimiters(String text){
        text = prepareText(text);
        for(String el: Constants.Delimiters)
        {
            int index = text.indexOf(el);
            if(index != -1)
                return new OutputMatch(index, index+el.length(), el);
        }
        return OutputMatch.Empty;
    }

    private static OutputMatch identifyKeyWords(String text){
        text = prepareText(text);
        OutputMatch result = applyAF(text, AutomateFinite.Header);
        if(!result.isEmpty())
            return result;
        for(String el: Constants.Keywords)
        {
            int index = text.indexOf(el);
            if(index != -1)
                return new OutputMatch(index, index+el.length(), el);
        }
        return OutputMatch.Empty;
    }

    private static OutputMatch identifyID(String text){
        text = prepareText(text);
        return applyAF(text, AutomateFinite.Identifier);
    }

    private static OutputMatch identifyLiteral(String text){
        text = prepareText(text);
        OutputMatch result = applyAF(text, AutomateFinite.String);
        if(!result.isEmpty())
            return result;
         result = applyAF(text, AutomateFinite.Number);
        if(!result.isEmpty())
            return result;
        return OutputMatch.Empty;
    }

    private static OutputMatch identifyAssigmentOperator(String text){
        text = prepareText(text);
        for(int i=0;i<text.length(); i++){
            char c = text.charAt(i);
            if(c == '=')
                return new OutputMatch(i, i + 1, Character.toString(c));
        }
        return OutputMatch.Empty;
    }

    private static OutputMatch identifyRelationOperator(String text){
        text = prepareText(text);
        for(String el : Constants.RelationOperators) {
            int index = text.indexOf(el);
            if(index != -1)
                return new OutputMatch(index, index+el.length(), el);
        }
        return OutputMatch.Empty;
    }

    private static OutputMatch identifyArithmeticOperator(String text){
        text = prepareText(text);
        for(String el : Constants.ArithmeticOperators) {
            int index = text.indexOf(el);
            if(index != -1)
                return new OutputMatch(index, index+el.length(), el);
        }
        return OutputMatch.Empty;
    }
}
