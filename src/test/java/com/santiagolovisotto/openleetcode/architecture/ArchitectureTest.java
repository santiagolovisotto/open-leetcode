package com.santiagolovisotto.openleetcode.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import java.util.List;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(
        packages = "com.santiagolovisotto.openleetcode",
        importOptions = ImportOption.DoNotIncludeTests.class)
class ArchitectureTest {

    private static final String BASE = "com.santiagolovisotto.openleetcode";
    private static final List<String> MODULES = List.of("auth", "challenges", "submissions");

    // --- Layers inside a module ---

    @ArchTest
    public static final ArchRule domain_does_not_depend_on_other_layers =
            noClasses().that().resideInAPackage("..modules..domain..")
                    .should().dependOnClassesThat()
                    .resideInAnyPackage("..modules..application..", "..modules..infrastructure..");

    @ArchTest
    public static final ArchRule application_does_not_depend_on_infrastructure =
            noClasses().that().resideInAPackage("..modules..application..")
                    .should().dependOnClassesThat().resideInAPackage("..modules..infrastructure..");

    @ArchTest
    public static final ArchRule inbound_adapters_do_not_depend_on_outbound_adapters =
            noClasses().that().resideInAPackage("..infrastructure.inbound..")
                    .should().dependOnClassesThat().resideInAPackage("..infrastructure.outbound..");

    // --- Frameworks stay out of the core ---

    @ArchTest
    public static final ArchRule domain_is_framework_free =
            noClasses().that().resideInAPackage("..modules..domain..")
                    .should().dependOnClassesThat()
                    .resideInAnyPackage("org.springframework..", "jakarta..");

    @ArchTest
    public static final ArchRule application_does_not_use_web_or_persistence_frameworks =
            noClasses().that().resideInAPackage("..modules..application..")
                    .should().dependOnClassesThat()
                    .resideInAnyPackage(
                            "org.springframework.web..",
                            "org.springframework.http..",
                            "org.springframework.data..",
                            "jakarta.persistence..");

    // --- Boundaries between modules ---

    @ArchTest
    static void only_the_application_layer_is_visible_from_other_modules(JavaClasses classes) {
        for (String module : MODULES) {
            noClasses().that().resideOutsideOfPackage(BASE + ".modules." + module + "..")
                    .should().dependOnClassesThat().resideInAnyPackage(
                            BASE + ".modules." + module + ".domain..",
                            BASE + ".modules." + module + ".infrastructure..")
                    .as("Other modules must only use the application layer of '" + module + "'")
                    .check(classes);
        }
    }

    @ArchTest
    public static final ArchRule auth_depends_on_no_other_module =
            noClasses().that().resideInAPackage(BASE + ".modules.auth..")
                    .should().dependOnClassesThat().resideInAnyPackage(
                            BASE + ".modules.challenges..",
                            BASE + ".modules.submissions..");

    @ArchTest
    public static final ArchRule challenges_does_not_depend_on_submissions =
            noClasses().that().resideInAPackage(BASE + ".modules.challenges..")
                    .should().dependOnClassesThat().resideInAPackage(BASE + ".modules.submissions..");

    @ArchTest
    public static final ArchRule modules_have_no_cycles =
            slices().matching(BASE + ".modules.(*)..").should().beFreeOfCycles();

    @ArchTest
    public static final ArchRule shared_does_not_depend_on_modules =
            noClasses().that().resideInAPackage(BASE + ".shared..")
                    .should().dependOnClassesThat().resideInAPackage(BASE + ".modules..");
}