package sg.edu.nus.iss.day27lecture;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.BucketOperation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.StringOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.client.result.UpdateResult;
import com.mongodb.internal.operation.AggregateOperation;

import sg.edu.nus.iss.day27lecture.model.Person;
import sg.edu.nus.iss.day27lecture.repo.PersonRepo;

@SpringBootApplication
public class Day27LectureApplication implements CommandLineRunner {

	@Autowired
	MongoTemplate mt;

	@Autowired
	PersonRepo personRepo;

	public static void main(String[] args) {
		SpringApplication.run(Day27LectureApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Person createdPerson01 = personRepo
				.insertPerson(new Person("Darryl", 15, "M", Arrays.asList("RC", "Swimmming", "Cycling")));
		Person createdPerson02 = personRepo
				.savePerson(new Person("Emily", 18, "F", Arrays.asList("Cycling", "Reading", "Shopping")));
		Person createdPerson03 = personRepo
				.insertPerson(new Person("James", 25, "M", Arrays.asList("RC", "Swimmming", "Cycling")));
		Person createdPerson04 = personRepo
				.savePerson(new Person("Camelia", 28, "F", Arrays.asList("Cycling", "Reading", "Shopping")));
		Person createdPerson05 = personRepo
				.insertPerson(new Person("Ashley", 35, "F", Arrays.asList("RC", "Swimmming", "Cycling")));
		Person createdPerson06 = personRepo
				.savePerson(new Person("Kristal", 88, "F", Arrays.asList("Cycling", "Reading", "Shopping")));

		// System.out.println("Created Person 02:" + createdPerson02.toString());

		// List<Person> persons = personRepo.getAllPersons();
		// personRepo.getPersonsPaginated(0, 10);
		// persons.forEach(System.out::println);

		// ObjectId _oid = new ObjectId("65a89fee6c4c6e0a3a398158");
		// Person updatePerson = new Person("65a89fee6c4c6e0a3a398158", "Nicole Kidman",
		// 45, "F", Arrays.asList("RC", "Swimmming", "Cycling"));
		// personRepo.findAndUpdatePerson(_oid, updatePerson);

		// day 27 - slide 13
		// Query query = new Query(Criteria.where("name").is("Emily"));

		// Update updateOps = new Update().set("name", "Emily Koh")
		// .set("age", 21)
		// .push("hobbies").each(List.of("Movies", "Eating").toArray());

		// UpdateResult results = mt.upsert(query, updateOps, "persons");
		// System.out.printf("Document updated: %d\n", results.getModifiedCount());

		// day 27 - slide 19
		// TextCriteria textCriteria =
		// TextCriteria.forDefaultLanguage().matchingPhrase("jaMEs");

		// TextQuery textQuery = TextQuery.queryText(textCriteria);

		// List<Person> resultTextQuery = mt.find(textQuery, Person.class, "persons");

		// System.out.println("Slide 19 (non case-sensitive): " +
		// resultTextQuery.toString());

		// textCriteria =
		// TextCriteria.forDefaultLanguage().matchingPhrase("jaMEs").caseSensitive(true);

		// textQuery = TextQuery.queryText(textCriteria);

		// List<Person> resultTextQuery2 = mt.find(textQuery, Person.class, "persons");

		// System.out.println("Slide 19 (case-sensitive): " +
		// resultTextQuery2.toString());

		// // day 27 - slide 22
		// textCriteria = TextCriteria.forDefaultLanguage().matchingPhrase("swimmming");

		// textQuery =
		// TextQuery.queryText(textCriteria).includeScore("score").sortByScore();

		// List<Document> resultTextQuery3 = mt.find(textQuery, Document.class,
		// "persons");

		// // doesn't affect much due low number of records
		// System.out.println("Slide 22 (non case-sensitive): " +
		// resultTextQuery3.toString());

		// day 28 - slide 9
		// MatchOperation matchOperation =
		// Aggregation.match(Criteria.where("Rated").is("PG"));

		// Aggregation pipeline = Aggregation.newAggregation(matchOperation);

		// AggregationResults<Document> results = mt.aggregate(pipeline, "movies",
		// Document.class);

		// List<Document> docs = results.getMappedResults();

		// System.out.println("Day 28 - slide 9: " + docs.toString());

		// day 28 - slide 11
		// MatchOperation matchOperation2 =
		// Aggregation.match(Criteria.where("Year").is("2009"));

		// ProjectionOperation projectionOperation = Aggregation.project("Title",
		// "Year", "Rated", "Released").andExclude("_id");

		// Aggregation pipeline2 = Aggregation.newAggregation(matchOperation2,
		// projectionOperation);

		// AggregationResults<Document> results2 = mt.aggregate(pipeline2, "movies",
		// Document.class);

		// List<Document> docs2 = results2.getMappedResults();

		// System.out.println("Day 28 - slide 10: " + docs2.toString());

		// day 28 - slide 15
		// GroupOperation groupOperation = Aggregation.group("Rated")
		// .push("Title").as("titles")
		// .count().as("count");

		// SortOperation sortOperation = Aggregation.sort(Sort.by(Direction.ASC,
		// "count"));

		// Aggregation pipeline = Aggregation.newAggregation(groupOperation,
		// sortOperation);

		// AggregationResults<Document> results = mt.aggregate(pipeline, "movies",
		// Document.class);

		// List<Document> docs = results.getMappedResults();
		// System.out.println("Day 28 - slide 15 " + docs.toString());

		// day 28 - slide 17
		// db.movies.aggregate([
		// {
		// $project: { (1)
		// _id: 1, Title: 1, summary: "$Type"
		// }
		// },
		// { (2)
		// $sort : { Title: 1, summary: -1}
		// }
		// ]);
		// day 28 - slide 18
		// (1)
		// ProjectionOperation projectionOperation = Aggregation.project("_id",
		// "Title").and("Type").as("summary");

		// // (2)
		// SortOperation sortOperation2 = Aggregation.sort(Sort.by(Direction.ASC,
		// "Title"));

		// // Step 3
		// Aggregation pipeline2 = Aggregation.newAggregation(projectionOperation,
		// sortOperation2);

		// // Step - execute pipeline using MongoTemplate
		// AggregationResults<Document> results2 = mt.aggregate(pipeline2, "movies",
		// Document.class);

		// List<Document> docs2 = results2.getMappedResults();
		// System.out.println("Day 28 - slide 18 " + docs2.toString());

		// day 28 - slide 21
		// ProjectionOperation projectionOperation = Aggregation.project("_id",
		// "title").and("Type").as("summary")
		// .and(StringOperators.Concat.valueOf("Title").concat(" ()")
		// .concatValueOf("Year").concat(") - ").concatValueOf("Rated"))
		// .as("title");

		// SortOperation sortOperation2 = Aggregation.sort(Sort.by(Direction.ASC,
		// "title"));

		// Aggregation pipeline2 = Aggregation.newAggregation(projectionOperation,
		// sortOperation);

		// AggregationResults<Document> results2 = mt.aggregate(pipeline, "movies",
		// Document.class);

		// List<Document> docs2 = results2.getMappedResults();
		// System.out.println("Day 28 - slide 21 " + docs2.toString());

		// day 28 - slide 27
		// AggregationOperation aggregateOperation = Aggregation.unwind("Genre");

		// GroupOperation groupOperation = Aggregation.group("Genre")
		// .push("Title").as("titles")
		// .count().as("title_count");

		// ProjectionOperation projectionOperation = Aggregation.project("_id",
		// "titles", "titles_count");

		// SortOperation sortOperation = Aggregation.sort(Sort.by(Direction.ASC,
		// "titles_count"));

		// Aggregation pipeline = Aggregation.newAggregation(aggregateOperation,
		// groupOperation, projectionOperation,
		// sortOperation);

		// AggregationResults<Document> results = mt.aggregate(pipeline, "movies",
		// Document.class);

		// List<Document> docs = results.getMappedResults();
		// System.out.println("Day 28 - slide 27 " + docs.toString());

		// day 28 - slide 29
		// db.movies.aggregate([
		// 	{
		// 		$bucket: {
		// 			groupBy: "$Rated",
		// 			boundaries: ["N/A", "PG", "R", "TV"],
		// 			default: "TV"
		// 		}
		// 	}
		// ]);
		// day 28 - slide 31

		// day 28 - slide 34
		BucketOperation bucketOperation = Aggregation.bucket("Rated")
				.withBoundaries("N/A", "PG", "R", "TV")
				.withDefaultBucket("TV")
				.andOutputCount().as("count")
				.andOutput("Title").push().as("titles");

		Aggregation pipeline = Aggregation.newAggregation(bucketOperation);

		AggregationResults<Document> results = mt.aggregate(pipeline, "movies", Document.class);

		List<Document> docs = results.getMappedResults();
		System.out.println("Day 28 - slide 34 " + docs.toString());

	}
}
