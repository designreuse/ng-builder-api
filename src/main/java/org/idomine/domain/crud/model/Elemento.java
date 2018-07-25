
package org.idomine.domain.crud.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.idomine.domain.crud.model.vo.TipoElemento;
import org.idomine.domain.crud.model.vo.TipoField;
import org.idomine.domain.crud.service.helper.FormlyHelper;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = { "id" })
public class Elemento
{
    @Id
    @GeneratedValue
    private Long id;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Artefato artefato;
    @Enumerated(EnumType.STRING)
    private TipoElemento tipoElemento;
    @Enumerated(EnumType.STRING)
    private TipoField tipoField;
    private boolean persistence;
    private boolean requerido;
    private String artefatoFK; // selectDB
    private String options;
    private long tamanho;
    private long decimais;
    private String nome;
    private String rotulo;
    private String inicial;
    private String mascara;
    private String pipe;
    private String dica;
    private long ordenation;
    private boolean showcolumn;

    public String tipoAngular()
    {
        return TipoElemento.SelectDB.equals(tipoElemento) ? nome : TipoField.angular(getTipoField()).toString();
    }

    public String tipoJava()
    {
        return TipoElemento.SelectDB.equals(tipoElemento) ? nome : tipoField.toString();
    }

    public boolean toForm()
    {
        return TipoElemento.Input.equals(tipoElemento) ||
                TipoElemento.TextArea.equals(tipoElemento) ||
                TipoElemento.Autocomplete.equals(tipoElemento) ||
                TipoElemento.Checkbox.equals(tipoElemento) ||
                TipoElemento.ButtonToggle.equals(tipoElemento) ||
                TipoElemento.Select.equals(tipoElemento) ||
                TipoElemento.SelectDB.equals(tipoElemento) ||
                TipoElemento.Datepicker.equals(tipoElemento) ||
                TipoElemento.RadioButton.equals(tipoElemento);
    }

    public String toFormly()
    {
        return FormlyHelper.toFormly(this);
    }

    public String requiredToString()
    {
        return requerido ? "true" : "false";
    }
    
    public boolean selectDB()
    {
        return TipoElemento.SelectDB.equals(tipoElemento);
    }

    public boolean hasOptions()
    {
        return options == null || options.trim().length() == 0 ? false : true;
    }

    public static List<Elemento> getFake1()
    {
        List<Elemento> lista = new ArrayList<>();

        // projeto
        lista.add(
                Elemento.builder()
                        .id(1L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("nome")
                        .rotulo("Nome")
                        .tamanho(100L)
                        .requerido(true)
                        .persistence(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(2L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("descricao")
                        .tamanho(100L)
                        .rotulo("Descrição")
                        .requerido(true)
                        .persistence(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(3L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("serverHost")
                        .tamanho(100L)
                        .rotulo("Server Host")
                        .requerido(true)
                        .persistence(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(4L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.Long)
                        .nome("serverPort")
                        .tamanho(4L)
                        .rotulo("Server port")
                        .requerido(true)
                        .persistence(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(4L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("frontHost")
                        .tamanho(100L)
                        .rotulo("front Host")
                        .requerido(true)
                        .persistence(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(5L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.Long)
                        .nome("frontPort")
                        .tamanho(4L)
                        .rotulo("Front port")
                        .requerido(true)
                        .persistence(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(6L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("nomeBackendApp")
                        .tamanho(100L)
                        .rotulo("Nome Backend App")
                        .requerido(true)
                        .persistence(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(8L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("nomeFrontEndApp")
                        .tamanho(100L)
                        .rotulo("Nome Frontend App")
                        .requerido(true)
                        .persistence(true)
                        .build());

        return lista;
    }

    // Artefato
    public static List<Elemento> getFake2()
    {
        List<Elemento> lista = new ArrayList<>();

        lista.add(
                Elemento.builder()
                        .id(9L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("nome")
                        .tamanho(50L)
                        .rotulo("Nome")
                        .requerido(true)
                        .persistence(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(10L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("resourceName")
                        .tamanho(50L)
                        .rotulo("Resource Name")
                        .requerido(true)
                        .persistence(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(11L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("className")
                        .tamanho(50L)
                        .rotulo("Class Name")
                        .requerido(true)
                        .persistence(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(12L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("classFolder")
                        .tamanho(50L)
                        .rotulo("Class Folder")
                        .requerido(true)
                        .persistence(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(12L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("templateTs")
                        .tamanho(999L)
                        .rotulo("Template Ts")
                        .requerido(true)
                        .persistence(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(12L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("templateHtml")
                        .tamanho(999L)
                        .rotulo("Template Html")
                        .requerido(true)
                        .persistence(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(12L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("templateCss")
                        .tamanho(999L)
                        .rotulo("Template Css")
                        .requerido(true)
                        .persistence(true)
                        .build());

        return lista;
    }

    // Elemento
    public static List<Elemento> getFake3()
    {
        List<Elemento> lista = new ArrayList<>();

        lista.add(
                Elemento.builder()
                        .id(13L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("nome")
                        .tamanho(50L)
                        .rotulo("Nome")
                        .requerido(true)
                        .persistence(true)
                        .showcolumn(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(14L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("rotulo")
                        .tamanho(50L)
                        .rotulo("Rótulo")
                        .requerido(true)
                        .persistence(true)
                        .showcolumn(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(15L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("inicial")
                        .tamanho(50L)
                        .rotulo("Valor Inicial")
                        .requerido(false)
                        .persistence(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(16L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("mascara")
                        .tamanho(100L)
                        .rotulo("Máscara Edição")
                        .requerido(false)
                        .persistence(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(17L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("pipe")
                        .tamanho(50L)
                        .rotulo("Máscara Display")
                        .requerido(false)
                        .persistence(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(18L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("dica")
                        .tamanho(200L)
                        .rotulo("Hint(dica)")
                        .requerido(false)
                        .persistence(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(13L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.SelectDB)
                        .tipoField(TipoField.String)
                        .nome("Artefato")
                        .tamanho(10L)
                        .rotulo("Artefato")
                        .requerido(true)
                        .persistence(true)
                        .options("{ value: 1, label: 'Arterfato 1'}, {value: 2, label: 'Artefato 2'}")
                        .build());
        lista.add(
                Elemento.builder()
                        .id(13L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Select)
                        .tipoField(TipoField.String)
                        .nome("tipoElemento")
                        .tamanho(10L)
                        .rotulo("Tipo Elemento")
                        .requerido(true)
                        .persistence(true)
                        .options(
                                "{ value: 1, label: 'Input'}, {value: 2, label: 'TextArea'}, {value: 3, label: 'Datepicker'}, {value: 4, label: 'Select'}, {value: 5, label: 'Checkbox'}")
                        .build());
        lista.add(
                Elemento.builder()
                        .id(13L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Select)
                        .tipoField(TipoField.String)
                        .nome("tipoField")
                        .tamanho(10L)
                        .rotulo("Tipo Field")
                        .requerido(true)
                        .persistence(true)
                        .options(
                                "{ value: 1, label: 'NotAvailable'}, {value: 2, label: 'String'}, {value: 3, label: 'Long'}, {value: 4, label: 'Date'}, {value: 5, label: 'Boolean'}")
                        .build());

        lista.add(
                Elemento.builder()
                        .id(13L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Checkbox)
                        .tipoField(TipoField.String)
                        .nome("requerido")
                        .tamanho(1L)
                        .rotulo("Requerido")
                        .requerido(true)
                        .persistence(true)
                        .nome("showColumn")
                        .build());

        lista.add(
                Elemento.builder()
                        .id(13L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Checkbox)
                        .tipoField(TipoField.String)
                        .nome("persistence")
                        .tamanho(1L)
                        .rotulo("Persistence")
                        .requerido(true)
                        .persistence(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(13L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.TextArea)
                        .tipoField(TipoField.String)
                        .nome("options")
                        .tamanho(1L)
                        .rotulo("Opções")
                        .persistence(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(19L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.Long)
                        .nome("tamanho")
                        .tamanho(20L)
                        .rotulo("Tamanho")
                        .requerido(true)
                        .persistence(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(19L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.Long)
                        .nome("decimais")
                        .tamanho(20L)
                        .rotulo("Decimais")
                        .requerido(true)
                        .persistence(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(19L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.Long)
                        .nome("ordenation")
                        .tamanho(5L)
                        .rotulo("Ordem")
                        .persistence(true)
                        .build());

        return lista;

    }

    // Config
    public static List<Elemento> getFake4()
    {
        List<Elemento> lista = new ArrayList<>();

        lista.add(
                Elemento.builder()
                        .id(23L)
                        .artefato(Artefato.builder().id(4L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("nomeEmpresa")
                        .tamanho(100L)
                        .rotulo("Nome Empresa")
                        .requerido(true)
                        .persistence(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(23L)
                        .artefato(Artefato.builder().id(4L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("siteEmpresa")
                        .tamanho(100L)
                        .rotulo("Site Empresa")
                        .requerido(false)
                        .persistence(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(25L)
                        .artefato(Artefato.builder().id(4L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("emailEmpresa")
                        .tamanho(100L)
                        .rotulo("Email Empresa")
                        .requerido(false)
                        .persistence(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(26L)
                        .artefato(Artefato.builder().id(4L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("outputDirectory")
                        .tamanho(100L)
                        .rotulo("Output Directory")
                        .requerido(false)
                        .persistence(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(27L)
                        .artefato(Artefato.builder().id(4L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("appProperties")
                        .tamanho(100L)
                        .rotulo("Application Properties")
                        .requerido(false)
                        .persistence(true)
                        .build());

        return lista;
    }

    // Tipo Elemento Component Formly
    public static List<Elemento> getFake5()
    {
        List<Elemento> lista = new ArrayList<>();

        lista.add(
                Elemento.builder()
                        .id(28L)
                        .artefato(Artefato.builder().id(5L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("campoText")
                        .tamanho(100L)
                        .showcolumn(true)
                        .rotulo("Campo Texto")
                        .persistence(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(28L)
                        .artefato(Artefato.builder().id(5L).build())
                        .tipoElemento(TipoElemento.TextArea)
                        .tipoField(TipoField.String)
                        .nome("campoTextArea")
                        .tamanho(100L)
                        .showcolumn(true)
                        .rotulo("Campo Text Area")
                        .persistence(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(29L)
                        .artefato(Artefato.builder().id(5L).build())
                        .tipoElemento(TipoElemento.Datepicker)
                        .tipoField(TipoField.Date)
                        .nome("campoData")
                        .tamanho(100L)
                        .showcolumn(true)
                        .rotulo("Campo Data")
                        .persistence(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(29L)
                        .artefato(Artefato.builder().id(5L).build())
                        .tipoElemento(TipoElemento.Checkbox)
                        .tipoField(TipoField.Boolean)
                        .nome("campoCheckbox")
                        .tamanho(100L)
                        .rotulo("Campo Checkbox")
                        .persistence(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(30L)
                        .artefato(Artefato.builder().id(5L).build())
                        .tipoElemento(TipoElemento.Select)
                        .tipoField(TipoField.String)
                        .nome("campoSelectOne")
                        .tamanho(100L)
                        .rotulo("Campo SelectOne")
                        .options(
                                "{ value: 1, label: 'Option 1'}, {value: 2, label: 'Option 2'}, {value: 3, label: 'Option 3'}")
                        .persistence(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(31L)
                        .artefato(Artefato.builder().id(5L).build())
                        .tipoElemento(TipoElemento.RadioButton)
                        .tipoField(TipoField.String)
                        .nome("campoRadioGroup")
                        .tamanho(100L)
                        .rotulo("Campo RadioGroup")
                        .options(
                                "{ value: 1, label: 'Option 1'}, {value: 2, label: 'Option 2'}, {value: 3, label: 'Option 3'}")
                        .persistence(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(32L)
                        .artefato(Artefato.builder().id(5L).build())
                        .tipoElemento(TipoElemento.ButtonToggle)
                        .tipoField(TipoField.String)
                        .nome("campoToggle")
                        .tamanho(100L)
                        .rotulo("Campo Toggle")
                        .persistence(true)
                        .build());

        return lista;
    }

}
