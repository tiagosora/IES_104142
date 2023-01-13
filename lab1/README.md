# Lab 2 - 104142

IES - Practical Classes - 22/23 - **Lab 2** - **P5** - **Tiago Carvalho** - **104142**



## What's Maven?

Maven is a popular open-source build tool developed by the Apache Group to build, publish, and deploy several projects at once for better project management. The tool provides allows developers to build and document the life cycle framework.

Maven focuses on the simplification and standardization of the building process, taking care of the following:

- Builds
- Documentation
- Dependencies
- Reports 
- SCMs
- Distribution
- Releases
- Mailing list

Maven’s purpose is to provide developers with:

- A comprehensive, maintainable, reusable, and simple model for projects.
- A set of tools and plug-ins that can interact with the declarative model.

Maven is based on the Project Object Model (POM).

[Simple Maven Introduction](simplilearn.com/tutorials/maven-tutorial) for beginners. 

**What is a Maven Archetype?**

Archetype is a Maven project templating toolkit. Archetypes get the users up and running giving them a sample project showing many Maven's features.

## Why do we use Maven?

Main advantages:

- Building the .jar, files for each project as there may be different versions of separate packages.

- Downloading and installing dependencies from mvnrepository.com. No need to download the dependencies via the official websites.

- Generating pre-built templates for different types of projects and create the right project structure, which is essential for execution.

- Building and deploying the project to make it work.

- Generating JavaDoc documentation in HTML format.

  

- Easy access to all the required information.

- Easy to run on the command line.

- Supported by all major IDEs.

For more information on Mavel capabilities, check [maven.apache.org](https://maven.apache.org/what-is-maven.html).

---

In order to confirm that Maven is successfully installed, as well as get some info about on the installation and the maven home path:

```bash
$ mvn --version
$ mvn -v
```

---

## What is POM?

A Project Object Model or POM is the fundamental unit of work in Maven. It's an XML file that has all the information regarding the Maven project and configuration details. It also includes the plugins used by Maven in the project.

Some of the configuration that can be specified in the POM are the project dependencies, the plugins or goals that can be executed, the build profiles, and so on. To add a dependency to POM we can install it on our IDE or go to an official repository and place the code between the marker.

For more information on POM, check [maven.apache.org](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html).

---

## Review questions

### **A) Maven has three life cycles: clean, site and default. Explain the main phases in the default life cycle.**

Maven's default life cycle is the main one as it's responsible for project deployment. It consists of many phases as the following ones:

1. **Validate**: Validating the project with all the necessary information is correct.
2. **Initialize**: Initializing the build directories.
3. **Generate-sources**: Generating the source code.
4. **Process-sources**: Processing of source code.
5. **Generate-resources**: Generating resources information.
6. **Process-resources**: Processing the resources.
7. **Compile**: Compilation of project structure.
8. **Process-classes**: Processing of generated compiled classes.
9. **Generate-test-sources**: Generating the test source code.
10. **Process-test-sources**: Processing of test source code.
11. **Generate-test-resources**: Generating the resource for testing.
12. **Process-test-classes**: Processing of compiled test class file.
13. **Test-compile**: Compilation of test source code.
14. **Process-test-classes**: Processing of compiled test class file.
15. **Test**: Run the generated test cases.
16. **Prepare-package**: Perform prepackaging operations.
17. **Package**: Perform the packaging in a distributed format like jar.
18. **Pre-integration-test**: Preparation for the integration test.
19. **Integration-test**: Performing integration test.
20. **Post-integration-test**: Perform the action required to perform after the integration test.
21. **Verify**: Verifying the generated distributed package.
22. **Install**: Install the package into the local repository.
23. **Deploy**: Copying the final build to a remote repository.

### **B) Maven is a build tool; is it appropriate to run your project to?**

Maven is a very effective build tool to use when developing a project, mainly for managing dependencies (libraries, plugins, etc) by helping us with the download and installation process where we just need to specify the dependencies we want to use; and for deploying the project, because Maven can compile, test, package the application according to the final format we want (this case, .jar).

### **C) What would be a likely sequence of Git commands required to contribute with a new feature to a given project? ( i.e., get a fresh copy, develop some increment, post back the added functionality )**

In order to access the project's repository in our computer we'd need to use the following commands:

If the repository have not been cloned yet, we need to clone it by going to the path we want the repository to be cloned and executing

```bash
$ git clone *giturl*
```

If the the project was already cloned, we might make sure we are working in the most recent version. To do so we pull it from the repository. Make sure you have no uncommitted changes when doing this step.

```bash
$ git pull
```

After that, it's time to add our modifications. Instead, of adding all one by one, it's also possible to the the second command and add all the changes at once.

```bash
$ git add modified_file
$ git add *
```

After staging all changes, we should commit them:

```bash
$ git commit -m "commit_description"
```

Finally, it's time to push the changes to the remote repository. If it fails to push the commit, it might be cause you're not working in the most recent version.

```bash
$ git push
```

### **D) There are strong opinions on how to write Git commit messages... Find some best practices online and give your own informed recommendations on how to write good commit messages ( in a team project ).**

The most important aspect we have to have in count when doing a commit is to make sure it's clear to everyone working on the project what changes we added. Some people say that commits should have generic names such as "updates" or  "new features".

Instead we should use a more direct vocabulary in order to specify what was made in the commit. To do that, we should, firstly, tell everyone what was changed and then follow it using words like "fix", "refactor", "test", "style"/"restyle", "update" .

`git commit -m "lab1 done"`

`git commit -m "lab1 read.me completed"`

`git commit -m "test.java function test fixed"`

There are other good guidelines that can help programmers achieve that charity. For instance:

- Remove unnecessary punctuation marks.
- Use the imperative mood in the subject line.
- Explain what changes you have made and why you made them.
- Make sure to specify the problem you fixed.
- Never think your code is self-explanatory.
- Talk with the other collaborators in order to establish sort of a commit name "template".

### **E) Docker automatically prepares the required volume space as you start a container. Why is it important that you take an extra step configuring the volumes for a (production) database?**

By default, all files created inside a container are automatically stored on a writable container layer. This means that data is lost when the container no longer exists. Volumes are the best way to persist data in Docker, because they are file systems mounted on containers to preserve data generated by the running container. Volumes are stored on the host, however it's possible to store the container's data on a remote host, which gives the possibility to share data more easily as well as process backups.

Volumes are not removed automatically by Docker. Even if there are no running container on the volume, unless we manually remove that volume, the data is preserved.

---

## Useful information, tutorials and explanations

Maven Build Lifecycle Explained - https://medium.com/javarevisited/maven-build-lifecycle-explained-ede8494a3d48
