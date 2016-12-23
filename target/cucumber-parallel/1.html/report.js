$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/CommentText.feature");
formatter.feature({
  "line": 1,
  "name": "Guinea Pig comment",
  "description": "",
  "id": "guinea-pig-comment",
  "keyword": "Feature"
});
formatter.before({
  "duration": 22559464702,
  "status": "passed"
});
formatter.scenario({
  "line": 3,
  "name": "Can submit comment",
  "description": "",
  "id": "guinea-pig-comment;can-submit-comment",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "I am on the Guinea Pig homepage",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "I submit a comment",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "I should see that comment displayed",
  "keyword": "Then "
});
formatter.match({
  "location": "GuineaPigSteps.user_is_on_guinea_pig_page()"
});
formatter.result({
  "duration": 1236533457,
  "status": "passed"
});
formatter.match({
  "location": "GuineaPigSteps.user_submit_comment()"
});
formatter.result({
  "duration": 1006571589,
  "status": "passed"
});
formatter.match({
  "location": "GuineaPigSteps.comment_displayed()"
});
formatter.result({
  "duration": 90796653,
  "status": "passed"
});
formatter.after({
  "duration": 264792992,
  "status": "passed"
});
});;