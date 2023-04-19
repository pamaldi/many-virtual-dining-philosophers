package cloud.isaura.dining.philosophers;

public enum PhilosopherType
{
    GREEK("GREEK"), GERMAN("GERMAN");

    private PhilosopherType(String type)
    {
        this.type = type;
    }

    private final String type;

    public String type()
    {
        return type;
    }

    public static PhilosopherType fromString(String type)
    {
        for(PhilosopherType philosopherType : PhilosopherType.values())
        {
            if(philosopherType.type().equals(type))
            {
                return philosopherType;
            }
        }
        return null;
    }
}
