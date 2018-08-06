package xmlswingpagefactory;

import xmlswingpagefactory.generators.DefaultFlowLayoutPanelGenerator;
import xmlswingpagefactory.generators.DefaultJButtonGenerator;
import xmlswingpagefactory.generators.DefaultJLabelGenerator;
import xmlswingpagefactory.interfaces.FlowLayoutPanelGenerator;
import xmlswingpagefactory.interfaces.JButtonGenerator;
import xmlswingpagefactory.interfaces.JLabelGenerator;
import xmlswingpagefactory.interfaces.TagRouter;

/**
 * Wrapper class that holds Delegates for XmlConversion Utilities, as well
 * as provides a default set of utilities
 */
public class ConverterSuite{
    public TagRouter router;
    public JButtonGenerator jButtonGenerator;
    public FlowLayoutPanelGenerator flowLayoutPanelGen;
    public JLabelGenerator jLabelGenerator;

    public static class Builder {
        TagRouter router = new DefaultTagRouter();
        FlowLayoutPanelGenerator flowLayoutPanelGenerator = new DefaultFlowLayoutPanelGenerator();
        JButtonGenerator jButtonGenerator = new DefaultJButtonGenerator();
        JLabelGenerator jLabelGenerator = new DefaultJLabelGenerator();

        public Builder routerDel(TagRouter router) {
            this.router = router;
            return this;
        }

        public Builder flowLayoutDel(FlowLayoutPanelGenerator layoutPanelGenerator) {
            this.flowLayoutPanelGenerator = layoutPanelGenerator;
            return this;
        }

        public Builder buttonDel(JButtonGenerator jButtonGenerator) {
            this.jButtonGenerator = jButtonGenerator;
            return this;
        }

        public Builder labelDel(JLabelGenerator jLabelGenerator) {
            this.jLabelGenerator = jLabelGenerator;
            return this;
        }

        public ConverterSuite build() {
            return new ConverterSuite(this);
        }
    }

    private ConverterSuite(Builder builder) {
        router = builder.router;
        flowLayoutPanelGen = builder.flowLayoutPanelGenerator;
        jButtonGenerator = builder.jButtonGenerator;
        jLabelGenerator = builder.jLabelGenerator;
    }

}
