package rs.mvd.filters;

import java.util.Map;
import java.util.Set;

public class ReportingModel {

    private long elapsedTime;
    private String methodName;
    private String path;
    private String endpoint;
    private int statusCode;
    private Map<String, String[]> parameters;

    public ReportingModel() {
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String[]> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String[]> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        String toString = "ReportingModel{" +
                "elapsedTime=" + elapsedTime +
                ", methodName='" + methodName + '\'' +
                ", path='" + path + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", statusCode=" + statusCode +
                ", parameters={";

        StringBuilder stringBuilder = new StringBuilder(toString);
        Set<String> keys = parameters.keySet();
        int i = 0;
        for (String key : keys) {
            QueryParam queryParam = new QueryParam(key, parameters.get(key)[0]);
            stringBuilder.append(queryParam.toString());
            if(i++ != keys.size() - 1){
                stringBuilder.append(",");
            }
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }


    private class QueryParam {
        String key;
        String value;

        QueryParam(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return
                    "key='" + key + '\'' +
                            ", value='" + value + '\'';
        }
    }

}
