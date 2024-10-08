package app.Slowly.LetterArchive.Letter;

import org.apache.tomcat.util.bcel.classfile.ClassFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class LetterController {
    List<String> MyCurrentFriends= new ArrayList<>();

    private final Service service;
    @Autowired
    public LetterController(Service service) {
        this.service = service;
    }

    // Put API endpoint to insert a new Letter Summary
    @RequestMapping(value = "/LetterSummary", method = RequestMethod.PUT)
    void InsertLetter(
            @RequestBody LetterSummary letterSummary
    )
    {
        if(letterSummary.getLetterSummary().isEmpty()||letterSummary.getUserName().isEmpty())
        {
            throw new ClassFormatException("It is required that the UserName and Letter Summary be present");
        }
        letterSummary.setLetterId(null);
        service.InsertLetter(letterSummary);
    }
    // Get API endpoint to find Letter Summary from the same UserName
    @RequestMapping(value = "/LetterSummary", method = RequestMethod.GET)
    List<LetterSummary> getLetterSummaryFromUserName(
            @RequestParam String UserName
    )
    {
        return service.getLetterSummaryFromUserName(UserName);
    }
    // Get API endpoint to find  all people I have as friend on the app
    @RequestMapping(value = "/Friends", method = RequestMethod.GET)
    List<String> getMyCurrentFriends()
    {
        return MyCurrentFriends;
    }
    // Delete API endpoint to remove all Letter Summaries with a specific name
    @RequestMapping(value = "/Friends", method = RequestMethod.DELETE)
    void deleteFriend(
            @RequestParam String UserName
    )
    {
        if (MyCurrentFriends.contains(UserName))
            service.removeFriend(UserName);
    }
    // Get API endpoint to get all letters
    @RequestMapping(value = "/Letters", method = RequestMethod.GET)
    List<LetterSummary> getAllLetters()
    {
        return service.getAllLetters();
    }
    // Update List of friends in a scheduled cron job every minute
    @Scheduled(cron = "*/30 * * * * *")
    void UpdateFriendList ()
    {
        MyCurrentFriends=new ArrayList<String>();
        MyCurrentFriends=service.retrieveAllNames();
    }
//    Patch API Endpoint to edit already inserted letter summaries by ID if need be
    @RequestMapping(value="/UseID/{ID}", method=RequestMethod.PUT)
    ResponseEntity<String> editLetter(
            @PathVariable Integer ID,
            @RequestBody String LetterSummary
    )
    {
        LetterSummary letterToEdit = service.getLetterByID(ID);
        letterToEdit.setLetterSummary(LetterSummary);
        service.InsertLetter(letterToEdit);
        return ResponseEntity.ok("Letter updated successfully");
    }
//    Get API Endpoint to retrieve by letter summary ID
@RequestMapping(value="/UseID/{ID}", method=RequestMethod.GET)
    LetterSummary getLetter (
            @PathVariable Integer ID
    )
    {
        return service.getLetterByID(ID);
    }

    @RequestMapping(value = "/ResetFromBackup", method = RequestMethod.POST)
    void reset()
    {
        //TODO: implement a way to reset from a csv file
        //later on we can make this a batch job, no need to worry about that for now
    }

}
