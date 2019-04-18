package sys2202.homework6;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

public class Homework6 {

	public static void main(String[] args) throws IOException {

		// set up a new mapreduce job for submission
		JobConf job = new JobConf(Homework6.class);
		job.setJobName("readingcount");

		// tell mapreduce where to find input and how to read it (as text)
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		job.setInputFormat(TextInputFormat.class);

		// tell mapreduce which mapper and reducer to use
		job.setMapperClass(Homework6Mapper.class);
		job.setReducerClass(Homework6Reducer.class);

		// tell mapreduce about the key and value types we're going to output
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		// tell mapreduce where to write output and how to write it (as text)
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		job.setOutputFormat(TextOutputFormat.class);

		// submit the job
		JobClient.runJob(job);
	}
}