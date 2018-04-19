package sys2202.hadoop.mapreduce.csv;

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

public class NameCount {

	/* 
	 * An example MapReduce program that processes CSV files of users and counts the number
	 * of occurrences of each first name.
	 * 
	 * This main method takes two arguments:  (1) the path to the input data (e.g., 
	 * a file or directory), and (2) the path to the output directory (must not
	 * already exist).
	 */
	public static void main(String[] args) throws IOException {

		// set up a new mapreduce job for submission
		JobConf job = new JobConf(NameCount.class);
		job.setJobName("namecount");

		// tell mapreduce where to find input and how to read it (as text)
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		job.setInputFormat(TextInputFormat.class);

		// tell mapreduce which mapper and reducer to use
		job.setMapperClass(NameCountMapper.class);
		job.setReducerClass(NameCountReducer.class);

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