package sg.edu.nus.iss.day27lecture.repo;

import java.util.List;

import org.bson.types.ObjectId;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import sg.edu.nus.iss.day27lecture.model.Person;

@Repository
public class PersonRepo {

    private final MongoTemplate mongoTemplate;

    public PersonRepo(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    //
    // db.persons.insert(
    // { _id: 1, name: "Tan Ah Khoon", age: 20, gender: "M", hobbies: [ "food", "electronics"] } 
    // );
    // day 27 - slide 3
    public Person insertPerson(Person person) {
        Person newPerson = mongoTemplate.insert(person);
        return newPerson;
    }

    public Person savePerson(Person person) {
        Person newPerson = mongoTemplate.save(person);
        return newPerson;
    }

    public List<Person> getAllPersons() {
        return mongoTemplate.findAll(Person.class);
    }

    public List<Person> getPersonsPaginated(int pageNumber, int pageSize) {
        Query query = new Query();
        query.skip(pageNumber * pageSize);
        query.limit(pageSize);

        return mongoTemplate.find(query, Person.class);
    }

    // day 27 - slide 5
    public void findAndDeletePerson(ObjectId id) {
        Query query = new Query(Criteria.where("_id").is(id));
        DeleteResult result = mongoTemplate.remove(query, "persons");

        System.out.printf("Deleted document: %d\n", result.getDeletedCount());
    }

    public void deletePerson(Person p) {
        mongoTemplate.remove(p);
    }

    public Person updatePerson(Person p) {
        Person updPerson = mongoTemplate.save(p);
        return updPerson;
    }

    // day 27 - slide 11 (skipped slide 9)
    public void findAndUpdatePerson(Long id, Person person) {
        Query query = Query.query(Criteria.where("_id").is(id));

        Update updateOperation = new Update()
        .set("name", person.getName())
        .inc("age", 1);

        UpdateResult result = mongoTemplate.updateMulti(query, updateOperation, "persons");

        System.out.printf("Documents updated: %d\n", result.getModifiedCount());
    }
}
