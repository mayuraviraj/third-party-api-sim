This is a simulator for third party issue tracking API.

System expose issuetracker/getissues POST API to retrieve data as we would find in 3rd party API.

Also issuetracker/feed POST API add to feed the data simulator. Once feeded getissues api would be able to deliver the data for invoker. This allows easy testing with
simualtor.

There is sample data added so that invoker can get data even without feeding data to simulator.