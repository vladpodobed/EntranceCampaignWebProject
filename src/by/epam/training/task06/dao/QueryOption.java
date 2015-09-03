package by.epam.training.task06.dao;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *      Allows build any query to db
 * </p>
 */
public class QueryOption {
    private String queryTemplate;
    private List<Object> parameters = new LinkedList<>();

    public QueryOption() {

    }

    public QueryOption(String queryTemplate, List<Object> parameters) {
        this.queryTemplate = queryTemplate;
        this.parameters = parameters;
    }

    public void clearParametersList() {
        this.parameters.clear();
    }

    public String getQueryTemplate() {
        return queryTemplate;
    }

    public void setQueryTemplate(String queryTemplate) {
        this.queryTemplate = queryTemplate;
    }

    public List<Object> getParameters() {
        return parameters;
    }

    public void setParameters(List<Object> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(Object parameter) {
        this.parameters.add(parameter);
    }
}
