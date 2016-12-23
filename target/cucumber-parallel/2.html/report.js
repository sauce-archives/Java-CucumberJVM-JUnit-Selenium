$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/FollowLink.feature");
formatter.feature({
  "line": 1,
  "name": "Guinea Pig link",
  "description": "",
  "id": "guinea-pig-link",
  "keyword": "Feature"
});
formatter.before({
  "duration": 22941826810,
  "status": "passed"
});
formatter.scenario({
  "line": 3,
  "name": "Can follow link to another page",
  "description": "",
  "id": "guinea-pig-link;can-follow-link-to-another-page",
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
  "name": "I click on the link",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "I should be on another page",
  "keyword": "Then "
});
formatter.match({
  "location": "GuineaPigSteps.user_is_on_guinea_pig_page()"
});
formatter.result({
  "duration": 1193808611,
  "status": "passed"
});
formatter.match({
  "location": "GuineaPigSteps.user_click_on_the_link()"
});
formatter.result({
  "duration": 672584075,
  "status": "passed"
});
formatter.match({
  "location": "GuineaPigSteps.new_page_displayed()"
});
formatter.result({
  "duration": 50784662,
  "status": "passed"
});
formatter.after({
  "duration": 278400911,
  "status": "passed"
});
});