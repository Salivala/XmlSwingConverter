package xmlswingpagefactory;

import xmlswingpagefactory.generators.*;
import xmlswingpagefactory.interfaces.*;

/**
 * Wrapper class that holds Delegates for XmlConversion Utilities, as well
 * as provides a default set of utilities
 */
public class ConverterSuite{
    public TagRouter router;
    public JButtonGenerator jButtonGenerator;
    public FlowLayoutGenerator flowLayoutPanelGen;
    public JLabelGenerator jLabelGenerator;
    public BorderLayoutGenerator borderLayoutGenerator;
    public BoxLayoutGenerator boxLayoutGenerator;

    public static class Builder {
        TagRouter router = new DefaultTagRouter();
        FlowLayoutGenerator flowLayoutPanelGenerator = new DefFlowLayoutGenerator();
        BorderLayoutGenerator borderLayoutGenerator = new DefBoarderLayoutGenerator();
        BoxLayoutGenerator boxLayoutGenerator = new DefBoxLayoutGenerator();
        JButtonGenerator jButtonGenerator = new DefJButtonGenerator();
        JLabelGenerator jLabelGenerator = new DefJLabelGenerator();

        public Builder routerDel(TagRouter router) {
            this.router = router;
            return this;
        }

        public Builder flowLayoutDel(FlowLayoutGenerator layoutPanelGenerator) {
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

        public Builder borderDel(BorderLayoutGenerator borderLayoutGenerator) {
            this.borderLayoutGenerator = borderLayoutGenerator;
            return this;
        }

        public Builder boxDel(BoxLayoutGenerator boxLayoutGenerator) {
            this.boxLayoutGenerator = boxLayoutGenerator;
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
        borderLayoutGenerator = builder.borderLayoutGenerator;
        boxLayoutGenerator = builder.boxLayoutGenerator;
    }

}
