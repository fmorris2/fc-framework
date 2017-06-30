package scripts.fc.framework.quest;

public abstract class QuestBool
{
	boolean normal;
	
	public QuestBool(boolean normal)
	{
		this.normal = normal;
	}
	
	public boolean validate()
	{
		if(normal)
			return value();
		
		return !value();
	}
	
	public QuestBool and(QuestBool o, boolean normal)
	{
		QuestBool first = this;
		return new QuestBool(normal)
		{
			@Override
			public boolean value()
			{
				return first.value() && o.value();
			}
			
		};
	}
	
	public QuestBool or(QuestBool o, boolean normal)
	{
		QuestBool first = this;
		return new QuestBool(normal)
		{
			@Override
			public boolean value()
			{
				return first.value() || o.value();
			}
			
		};
	}
	
	public abstract boolean value();
}
