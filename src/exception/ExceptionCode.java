package exception;

public enum ExceptionCode {

    RACER_NAME_DUPLICATE("레이서의 이름은 중복되어서는 안됩니다."),
    RACER_NAME_LENGTH_LIMIT("레이서의 이름은 0글자 이상 10글자 이하여야 합니다.");

    private final String errorMsg;

    ExceptionCode(String errorMsg){
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
