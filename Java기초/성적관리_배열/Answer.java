class Subject{
	private String name;
	private int score;

	//������ ã���� ���
	// = public boolean equals(Object o)
	//Ư�������� ã����
	//�˻���
	public Subject(String name){
		setName(name);
	}
	
	//����� ������ �޾Ҵٰ� Ȯ�ο�
	public Subject(String name, int score){
		setName(name);
		setScore(score);
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public int getScore(){
		return score;
	}

	public void setScore(int score){
		this.score = score;
	}

	@Override 
	public String toString(){
		return name + " : " + score;
	}

	//�̸��� ������ ���� ��������.
	@Override
	public boolean equals(Object o){
		if(o == null || !(o instanceof Subject)){
			return false;
		}
		Subject temp = (Subject)o;
		//��������� �������� ������ ���ϴ� ������ ã�����ؼ�
		return name.equals(temp.getName());
	}
}

class Student{
	private String name; //�л� �̸�
	private Subject[] scores; //������������ ������ ������ �ֱ⶧���� �迭

	//������ �迭
	public Student(String name, Subject... scores){
		setName(name);
		setScores(scores);
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Subject[] getScores(){
		return scores;
	}

	public void setScores(Subject... scores){
		this.scores = scores;
	}
	//���κ� ����
	public int getTotal(){
		int total = 0;
		//Subject���ִ� ������ �������迭�� �������� �� �ҷ��ͼ� ������.
		//scores Subject�迭���ִ°��� �ϳ��� �ҷ��ͼ� ���� ����
		for(Subject temp : scores){
			total += temp.getScore();
		}
		return total;
	}

	//���κ� ���
	//���κ� �������� / ���񰳼���ŭ(������ ���Ҽ��� ����) ������ ����� ����
	public int getAvg(){
		return getTotal() / scores.length;
	}

	/*
		������ ���� ã��
		param : ������ �̸�
		return : ����. ��, �������� �ʴ� �������� ��� -1
	*/

	//Ư���������� �̸��� �����ָ� �������� ������ ������
	//-1�� ������ ������ ���� ���������� 0���̶� -1
	//�������ϴ� �������� ������ ������
	public int getSubjectScore(String name){
		//�������̸��� �޾Ƽ� Subject��ü ����
		Subject goal = new Subject(name); //�����̸�
		int score = -1;
		for(Subject temp : scores){ //�̸��� ���� �������� ã�Ƽ� ������ ����
			if(temp.equals(goal)){ //temp�� ������ ������ �ִ��� ������ Ȯ��
				score = temp.getScore(); //���� �ִٸ� ������ �ְ� ����
			}
		}
		return score;
	}
	
	//�̸� ���
	//���� �ϳ��� �ҷ��ͼ� ���
	//���� ���
	//��� ���
	@Override
	public String toString(){
		String info ="<<" + name + ">>\n";
		for(Subject temp : scores){
			info += temp.toString() + "\n";
		}
		info += "���� : " + getTotal() + "\n";
		info += "��� : " + getAvg();

		return info;
	}
}

//�б�
//�����񺰷� �����ϱ����ؼ� �л�
class Klass {

		private Student[] list;

		public Klass(Student... list){
			setList(list);
		}
		public Student[] getList(){
			return list;
		}
		
		public void setList(Student... list){
			this.list = list;
		}
		
		//������ ����
		public int getTotal(String subjectName){ //�������̸��� �ް�
			int total = 0;
			for(Student temp : list){//list�� �л����(�л� �Ѹ� ���ͼ� Student��
				int score = temp.getSubjectScore(subjectName);
				if(score >= 0){ //���� -1�� ������. ������ �����ϸ� ������� ������ ���Ѷ�
					total += score;
				}
			}
			return total;
		}
			
		//������ ���
		public int getAvg(String subjectName){ //�������̸� �޾Ƽ� �ձ��ϰ� �л�����ŭ ������
			return getTotal(subjectName) / list.length;
		}

		@Override
		public String toString(){
			String info ="- �б����� -\n";
			for(Student temp : list){
				info += temp.toString() + "\n";
			} 
			//���� �б��� �л��� ���� ������ �����Ƿ�...
			//list[0]�� ù��° �л��� Subject����
			Subject[] scores = list[0].getScores();
			for(Subject temp : scores){
				String name = temp.getName(); //Subject�̸��ʿ���
				info += name + " ���� : " + getTotal(name) + "\n";
				info += name + "��� : " + getAvg(name) + "\n";
			}
			return info;
		}	
}

class Answer{
	public static void main(String[] args)	{
		Subject s1 = new Subject("����", 90);
		Subject s2 = new Subject("����", 90);
		Subject s3 = new Subject("����", 90);

		Student st1 = new Student("�ֺ���",s1,s2,s3);
		Student st2 = new Student(
			"�����",
			new Subject("����",90),
			new Subject("����",90),
			new Subject("����",90)
		);
		Student st3 = new Student(
			"������",
			new Subject("����",100),
			new Subject("����",100),
			new Subject("����",100)
			);

		System.out.println(new Klass(st1,st2,st3));
	}
}