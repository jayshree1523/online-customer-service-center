package com.company.onlinecustomerservicecenter.issue;

import com.company.onlinecustomerservicecenter.solution.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService{

    @Autowired
    private IssueRepository issueRepository;

    @Override
    public Issue createIssue(Issue issue) throws IssueException {
        Optional<Issue> issueOpt = this.issueRepository.findByIssueId(issue.getIssueId());
        if(issueOpt.isPresent()){
            throw new IssueException("Issue already exists with id: "+issueOpt.get().getIssueId());
        }
        this.issueRepository.save(issue);
        return issue;

    }

    @Override
    public Issue updateIssue(Integer issueId, String issueDescription, String description, Solution issueSolution) throws IssueException {
        Optional<Issue> issueOpt = this.issueRepository.findByIssueId(issueId);
        if(issueOpt.isEmpty()){
            throw new IssueException("Issue does not exist with id: "+issueId);
        }
        Issue issue = issueOpt.get();
        issue.setIssueType("In-Progress");
        issue.setDescription(issueDescription + issue.getDescription());
        issue.setSolution(issueSolution);
        this.issueRepository.save(issue);

        return issue;

    }

    @Override
    public Issue deleteIssue(Integer issueId) throws IssueException {
        Optional<Issue> issueOpt = this.issueRepository.findByIssueId(issueId);
        if(issueOpt.isEmpty()){
            throw new IssueException("Issue does not exist with id: "+issueId);
        }
        Issue issue = issueOpt.get();
        this.issueRepository.deleteById(issueId);
        return issue;

    }

    @Override
    public Issue closeIssue(Integer issueId) throws IssueException {
        Optional<Issue> issueOpt = this.issueRepository.findByIssueId(issueId);
        if(issueOpt.isEmpty()){
            throw new IssueException("Issue does not exist with id: "+issueId);
        }
        Issue issue = issueOpt.get();
        issue.setIssueType("Closed");
        issue.setDescription("Closed: "+issue.getDescription());
        return issue;
    }

    @Override
    public Issue reOpenIssue(Integer issueId, String issueDescription) throws IssueException {
        Optional<Issue> issueOpt = this.issueRepository.findByIssueId(issueId);
        if(issueOpt.isEmpty()){
            throw new IssueException("Issue does not exist with id: "+issueId);
        }
        Issue issue = issueOpt.get();
        issue.setIssueType("Re-Opened");
        issue.setDescription("Re-Opened: "+issueDescription + issue.getDescription());
        return issue;
    }

    @Override
    public Issue viewIssue(Integer issueId) throws IssueException {
        Optional<Issue> issueOpt = this.issueRepository.findByIssueId(issueId);
        if(issueOpt.isEmpty()){
            throw new IssueException("Issue does not exist with id: "+issueId);
        }
        return issueOpt.get();

    }

    @Override
    public List<Issue> getAllIssues() throws IssueException {
        return this.issueRepository.findAll();
    }

}