��Android��ʵ���첽������������ַ�ʽ��Handler��AsyncTask��
Handlerģʽ��ҪΪÿһ�����񴴽�һ���µ��̣߳�������ɺ�ͨ��Handlerʵ����UI�̷߳�����Ϣ����ɽ���ĸ��£����ַ�ʽ�����������̵Ŀ��ƱȽϾ�ϸ����Ҳ����ȱ��ģ�����������ӷ�ף��ڶ������ͬʱִ��ʱ�����׶��߳̽��о�ȷ�Ŀ��ơ�����Handler�����֪ʶ��ǰ��Ҳ�������ܣ�������������ǿ��Բ���һ�¡�
Ϊ�˼򻯲�����Android1.5�ṩ�˹�����android.os.AsyncTask����ʹ�����첽�����ø��Ӽ򵥣�������Ҫ��д�����̺߳�Handlerʵ�����������ͬ������

��������AsyncTask�Ķ��壺
public abstract class AsyncTask<Params, Progress, Result> 

���ַ������ͷֱ������������ִ�е����������������̨����ִ�еĽ��ȡ�������̨�����������͡������ض������£��������������Ͷ���ʹ�ã����û�б�ʹ�ã�������java.lang.Void���ʹ��档
һ���첽�����ִ��һ��������¼������裺
1.execute(Params... params)��ִ��һ���첽������Ҫ�����ڴ����е��ô˷����������첽�����ִ�С�
2.onPreExecute()����execute(Params... params)�����ú�����ִ�У�һ��������ִ�к�̨����ǰ��UI��һЩ��ǡ�
3.doInBackground(Params... params)����onPreExecute()��ɺ�����ִ�У�����ִ�н�Ϊ��ʱ�Ĳ������˷�����������������ͷ��ؼ���������ִ�й����п��Ե���publishProgress(Progress... values)�����½�����Ϣ��
4.onProgressUpdate(Progress... values)���ڵ���publishProgress(Progress... values)ʱ���˷�����ִ�У�ֱ�ӽ�������Ϣ���µ�UI����ϡ�
5.onPostExecute(Result result)������̨��������ʱ���˷������ᱻ���ã�����������Ϊ�������ݵ��˷����У�ֱ�ӽ������ʾ��UI����ϡ�
��ʹ�õ�ʱ���м�����Ҫ����ע�⣺
1.�첽�����ʵ��������UI�߳��д�����
2.execute(Params... params)����������UI�߳��е��á�
3.��Ҫ�ֶ�����onPreExecute()��doInBackground(Params... params)��onProgressUpdate(Progress... values)��onPostExecute(Result result)�⼸��������
4.������doInBackground(Params... params)�и���UI�������Ϣ��
5.һ������ʵ��ֻ��ִ��һ�Σ����ִ�еڶ��ν����׳��쳣��